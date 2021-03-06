package com.example.onedaypiece.util;

import com.example.onedaypiece.web.domain.challenge.Challenge;
import com.example.onedaypiece.web.domain.challenge.ChallengeRepository;
import com.example.onedaypiece.web.domain.challengeRecord.ChallengeRecord;
import com.example.onedaypiece.web.domain.challengeRecord.ChallengeRecordRepository;
import com.example.onedaypiece.web.domain.posting.Posting;
import com.example.onedaypiece.web.domain.posting.PostingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class Scheduler {

    final PostingRepository postingRepository;
    private final ChallengeRecordRepository challengeRecordRepository;

    private final LocalDateTime today = LocalDate.now().atStartOfDay();

    //    01 00 00
    @Scheduled(cron = "01 00 00 * * *") // 초, 분, 시, 일, 월, 주 순서
    @Transactional
    public void postingStatusUpdate() {
        List<Posting> postingList = postingRepository.findSchedulerPosting(today);

        // 벌크성 쿼리 업데이트
        int updateResult = postingRepository.updatePostingStatus(postingList);

        log.info("updateResult 벌크 연산 result: {} ", updateResult);
    }

    @Scheduled(cron = "01 00 00 * * *") // 초, 분, 시, 일, 월, 주 순서
    @Transactional
    public void challengeStatusUpdate() {
        List<ChallengeRecord> records = challengeRecordRepository.findAllByChallengeStatusTrue();
        List<Challenge> updatedChallengeList = new ArrayList<>();

        for (ChallengeRecord record : records) {
            Challenge challenge = record.getChallenge();
            if (!updatedChallengeList.contains(challenge)) {
                updatedChallengeList.add(challenge);

                if (challenge.getChallengeProgress() == 1L && setTimeToZero(challenge.getChallengeStartDate()).isEqual(today)) {
                    challenge.setChallengeProgress(2L);
                    log.info(challenge.getChallengeId() + " 챌린지의 progress 1 -> " + challenge.getChallengeProgress());
                } else if (challenge.getChallengeProgress() == 2L && setTimeToZero(challenge.getChallengeEndDate()).isEqual(today)) {
                    challenge.setChallengeProgress(3L);
                    record.setStatusFalse();
                    log.info(challenge.getChallengeId() + " 챌린지의 progress가 2 -> " +  + challenge.getChallengeProgress());
                    log.info(record + " 챌린지기록의 status가 " + record.isChallengeRecordStatus());
                }
            }
        }
    }

    private LocalDateTime setTimeToZero(LocalDateTime time) {
        return time.withHour(0).withMinute(0).withSecond(0);
    }
}
