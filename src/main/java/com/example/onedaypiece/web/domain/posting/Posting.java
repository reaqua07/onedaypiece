package com.example.onedaypiece.web.domain.posting;

import com.example.onedaypiece.web.domain.challenge.Challenge;
import com.example.onedaypiece.web.domain.common.Timestamped;
import com.example.onedaypiece.web.domain.member.Member;
import com.example.onedaypiece.web.dto.request.PostingRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Posting extends Timestamped {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long postingId;
    @Column
    private String postingImg;
    @Column
    private String postingContent;
    @Column
    private boolean postingStatus;
    @Column
    private boolean postingApproval;
    @Column
    private boolean postingModifyOk;
    @Column
    private boolean postingPoint;
    @Column
    private Long postingCount;

    @ManyToOne
    private Member member;
    @ManyToOne
    private Challenge challenge;

    public Posting(String postingImg, String postingContent, Member member, Challenge challenge) {
        this.postingImg = postingImg;
        this.postingContent =postingContent;
        this.postingStatus = true;
        this.postingApproval=false;
        this.postingPoint=false;
        this.postingModifyOk=true;
        this.postingCount=0L;
        this.member =member;
        this.challenge=challenge;
    }


    public static Posting createPosting(PostingRequestDto postingRequestDto, Member member, Challenge challenge) {

        return new Posting(
                postingRequestDto.getPostingImg(),
                postingRequestDto.getPostingContent(),
                member,
                challenge
        );
    }
}