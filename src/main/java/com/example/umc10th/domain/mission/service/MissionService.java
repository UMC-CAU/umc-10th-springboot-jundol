package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import org.springframework.stereotype.Service;

@Service
public class MissionService {
    public MissionResDTO.GetHome getHome(MissionReqDTO.Home dto) {
        return null; //임의 생성
    }

    public MissionResDTO.ViewMissions viewMissions(MissionReqDTO.ViewMissions dto) {
        return null;
    }

    public MissionResDTO.MissionComplete missionComplete(MissionReqDTO.MissionComplete dto) {
        return null;
    }
}
