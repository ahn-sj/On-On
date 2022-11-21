package com.ono.omg.controller;

import com.ono.omg.dto.common.ResponseDto;
import com.ono.omg.dto.response.OrderResponseDto;
import com.ono.omg.dto.response.OrderResponseDto.MainPageOrdersResponseDto;
import com.ono.omg.security.user.UserDetailsImpl;
import com.ono.omg.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class MyPageController {

    private final OrderService orderService;

    @GetMapping("/mypage/orders")
    public ResponseDto<List<MainPageOrdersResponseDto>> findAllOrdersParticularAccount(@PageableDefault(size = 10) Pageable pageable,
                                                                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseDto.success(orderService.findAllOrders(pageable, userDetails.getAccount()));
    }
}
