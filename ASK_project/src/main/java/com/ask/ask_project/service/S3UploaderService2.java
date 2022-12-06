package com.ask.ask_project.service;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ask.ask_project.DTO.Expense_infoDTO;
import com.ask.ask_project.DTO.IncomeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3UploaderService2 {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // [ 업로드 과정.. ]
    public IncomeDTO upload(IncomeDTO expense_infoDTO, MultipartFile multipartFile, String dirName,int count) throws IOException {
        // 변환 실패 시 예외처리
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));

        return upload(expense_infoDTO, uploadFile, dirName, count);
    }

    // [ --- 최종 업로드 과정 --- ]
    private IncomeDTO upload(IncomeDTO incomeDTO, File uploadFile, String dirName, int count) {
        String today = new SimpleDateFormat("yyMMdd").format(new Date());

        // 파일 이름 설정(여기서 우리가 원하는 형식으로 바꾼다.)
        //회사코드 + today날짜 + taxbill + count
        String fileName = dirName + "/"+incomeDTO.getCompCode()+today+"taxBill"+count;
        // s3로 파일 저장
        String uploadImageUrl = putS3(uploadFile, fileName);
        if(uploadImageUrl == null){
            return null;
        }

        // 로컬에 저장한 File타입 임시 파일 삭제
        removeNewFile(uploadFile);

        // url값 세팅
        incomeDTO.setTaxBill(uploadImageUrl);

        //Expense_infoDTO 로 반환해준다.
        return incomeDTO;
    }


    // [ 전환된 File을 S3에 public 권한으로 put(외부에서 정적 파일을 읽을 수 있도록 하기 위함) ]
    // [ s3로 넘어가는 부분 ]
    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(
                CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }


    // s3 파일 삭제하기
    // ( 파일 이름 형식 예시 : https://elasticbeanstalk-ap-northeast-2-299253517068.s3.ap-northeast-2.amazonaws.com/receipt/admin01_20221204_008_2714500582_5 )
    public void deleteS3(String fileName){
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
        System.out.println("----- s3에서 해당 이름의 파일을 삭제하였습니다 -----");
    }




    // [ 로컬에 임시로 만들어둔 file타입의 파일 삭제 ]
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }



    // [ MultipartFile을 File로 변환 ]
    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }
}
