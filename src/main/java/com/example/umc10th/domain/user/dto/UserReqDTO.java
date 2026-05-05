package com.example.umc10th.domain.user.dto;


import com.example.umc10th.domain.user.enums.FoodList;
import com.example.umc10th.domain.user.enums.Gender;
import com.example.umc10th.domain.user.enums.Term;

import java.time.LocalDate;
import java.util.List;

public class UserReqDTO {


    //마이 페이지
    public record GetInfo(
            Long id
    ){}


    //회원 가입
    public record SignUp(
            String name,
            Gender gender,
            LocalDate birth,
            String location,
            String detailAddress,
            String email,
            String password,
            List<FoodList> foods,
            List<Term> terms
    ) {}
}
