package com.app.gradationback.mapper.sehyun;

import com.app.gradationback.domain.AuctionDTO;
import com.app.gradationback.domain.AuctionVO;
import com.app.gradationback.mapper.AuctionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
@Slf4j
public class AuctionMapperTest {

    @Autowired
    private AuctionMapper auctionMapper;

    @Test
    public void insert() {
        AuctionVO auctionVO = new AuctionVO();
        auctionVO.setArtId(9L);
        auctionVO.setAuctionStartDate("2025-04-24 16:30:00");
        auctionVO.setAuctionStartPrice(1000000L);
        auctionVO.setAuctionEstimatedMinPrice("2000000");
        auctionVO.setAuctionEstimatedMaxPrice("3000000");

        auctionMapper.insert(auctionVO);
        log.info(auctionVO.toString());

    }

    @Test
    public void selectAll() {
        HashMap<String, Object> params = new HashMap<>();
//        List<AuctionDTO> auctions = auctionMapper.selectAll(params);
//        log.info("------------------------------------");
//        for (AuctionDTO auction : auctions) {
//            log.info(auction.toString());
//        }
//        log.info("------------------------------------");
        params.put("order", "date");
        params.put("cursor", 1);
        params.put("direction", "asc");
        params.put("category", "공예");
        params.put("status", "bidding");
        List<AuctionDTO> auctions = auctionMapper.selectAll(params);
        log.info("------------------------------------");
        for (AuctionDTO auction : auctions) {
            log.info(auction.toString());
        }
        log.info("------------------------------------");
    }

    @Test
    public void select() {
//        List<AuctionDTO> auctionDTO = auctionMapper.select(1L);
//        log.info("-------------------------------------");
//        for (AuctionDTO auction : auctionDTO) {
//            log.info(auction.toString());
//        }
//        log.info("------------------------------------");
    }

    @Test
    public void selectBiddingTest() {
        List<AuctionDTO> auctionDTOS = auctionMapper.selectBidding(1);
        log.info("------------------------------------");
        for (AuctionDTO auction : auctionDTOS) {
            log.info(auction.toString());
        }
        log.info("------------------------------------");
    }

    @Test
    public void update() {
//        AuctionVO auctionVO = new AuctionVO();
//        auctionVO.setId(2L);
//        auctionVO.setAuctionStartDate("2025-04-12 16:30:00");
//        auctionVO.setAuctionStartPrice(100000);
//        auctionVO.setAuctionEstimatedMinPrice("200000");
//        auctionVO.setAuctionEstimatedMaxPrice("300000");
//
//        auctionVO.setAuctionAttracted(true);
//        auctionVO.setAuctionBidPrice(400000);
//        auctionVO.setAuctionBidDate("2025-04-12 16:30:00");
//        auctionVO.setUserId(3L);
//
//        log.info(auctionVO.toString());
//
//        auctionMapper.update(auctionVO);
        AuctionVO auctionVO = new AuctionVO();
        auctionVO.setId(1L);
        auctionVO.setAuctionStartDate("2025-05-26 14:40:00");
        auctionVO.setAuctionEndDate("2025-05-28 14:40:00");
        auctionVO.setAuctionStartPrice(1000000L);
        auctionVO.setAuctionEstimatedMinPrice("2000000");
        auctionVO.setAuctionEstimatedMaxPrice("3000000");

        auctionMapper.update(auctionVO);
    }

    @Test
    public void delete() {
        auctionMapper.delete(3L);
    }


}
