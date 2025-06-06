package com.app.gradationback.mapper;

import com.app.gradationback.domain.DeliveryDTO;
import com.app.gradationback.domain.PaymentCancellationVO;
import com.app.gradationback.domain.PaymentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PaymentMapper {
    public void insert(PaymentVO paymentVO);
    public void insertCancel(PaymentCancellationVO paymentCancellationVO);
    public Optional<DeliveryDTO> select(Long id);
    public Optional<DeliveryDTO> selectByAuctionId(Long auctionId);
    public List<DeliveryDTO> selectByUserId(Long userId);
}
