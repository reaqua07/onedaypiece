package com.example.onedaypiece.web.dto.response.challenge;

import com.example.onedaypiece.web.domain.challenge.CategoryName;
import com.example.onedaypiece.web.domain.challenge.Challenge;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
public class ChallengeResponseDto {

    private Long challengeId;
    private Long memberId;
    private String memberName; // 챌린지 만든 사람
    private String challengeTitle;
    private String challengeContent;
    private CategoryName categoryName; // enum
    private String challengePassword;
    private LocalDateTime challengeStartDate;
    private LocalDateTime challengeEndDate;
    private Long challengeProgress;
    private String challengeImgUrl;
    private String challengeGood;
    private String challengeBad;
    private String challengeHoliday;
    private final Set<Long> challengeMember = new HashSet<>();

    public ChallengeResponseDto(Challenge challenge,
                                List<Long> challengeMember) {
        this.memberId = challenge.getMember().getMemberId();
        this.challengeId = challenge.getChallengeId();
        this.memberName = challenge.getMember().getNickname();
        this.challengeTitle = challenge.getChallengeTitle();
        this.challengeContent = challenge.getChallengeContent();
        this.categoryName = challenge.getCategoryName();
        this.challengePassword = challenge.getChallengePassword();
        this.challengeStartDate = challenge.getChallengeStartDate();
        this.challengeEndDate = challenge.getChallengeEndDate();
        this.challengeProgress = challenge.getChallengeProgress();
        this.challengeImgUrl = challenge.getChallengeImgUrl();
        this.challengeGood = challenge.getChallengeGood();
        this.challengeBad = challenge.getChallengeBad();
        this.challengeHoliday = challenge.getChallengeHoliday();
        this.challengeMember.addAll(challengeMember);
    }
}
