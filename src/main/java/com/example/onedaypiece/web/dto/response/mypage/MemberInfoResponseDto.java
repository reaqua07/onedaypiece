package com.example.onedaypiece.web.dto.response.mypage;


import com.example.onedaypiece.web.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 나중에코드수정하고싶을때 이거사용하기 이걸로 다담아서보내면편함 하지만 프론트도 수정해야함 받는거는달라지니까
@NoArgsConstructor
@Getter
public class MemberInfoResponseDto {
    private Long memberId;
    private String nickname;
    private String profileImg;
    private Long point;
    private Long memberLevel; // 멤버 레벨 계산해서하기

    public MemberInfoResponseDto(Member member){
        this.memberId = member.getMemberId();
        this.nickname = member.getNickname();
        this.profileImg = member.getProfileImg();
        this.point = member.getPoint().getAcquiredPoint();
        this.memberLevel = calculLevel(member.getPoint().getAcquiredPoint());
    }

    // 699면 5레벨
    public long calculLevel(Long memberPoint){
        long level = 1;

        //100 1~5
        if(memberPoint < 500) {
            level = memberPoint / 100;
            level +=1;
        }
        //200 6~10
        if(memberPoint>=500 && memberPoint<1500){
            level = (memberPoint-500) / 200;
            System.out.println(level);
            level += 5+1;
        }
        //300 11~15
        if(memberPoint>=1500 && memberPoint < 3000){
            level = (memberPoint - 1500) / 300;
            System.out.println(level);
            level += 10+1;
        }
        //400 16~20
        if(memberPoint>=3000 && memberPoint<5000){
            level = (memberPoint - 3000) / 400 ;
            System.out.println(level);
            level += 15+1;
        }
        //500 21~25
        if(memberPoint>=5000 && memberPoint<7500){
            level = (memberPoint - 5000) / 500 ;
            System.out.println(level);
            level += 20+1;
        }

        //600 26~30
        if(memberPoint>=7500 && memberPoint<10500){
            level = (memberPoint - 7500) / 600 ;
            System.out.println(level);
            level += 25+1;
        }

        //700 31~35
        if(memberPoint>=10500 && memberPoint<14000){
            level = (memberPoint - 10500) / 700 ;
            System.out.println(level);
            level += 30+1;
        }

        //800 36~40
        if(memberPoint>=14000 && memberPoint<18000){
            level = (memberPoint - 14000) / 800 ;
            System.out.println(level);
            level += 35+1;
        }

        //900 41~45
        if(memberPoint>=18000 && memberPoint<22500){
            level = (memberPoint - 18000) / 900 ;
            System.out.println(level);
            level += 40+1;
        }

        //1000 46~50
        if(memberPoint>=22500 && memberPoint<27500){
            level = (memberPoint - 22500) / 1000 ;
            System.out.println(level);
            level += 45+1;
        }

        if(memberPoint>=27500){
            level = 50;
        }
        return level;
    }
}
