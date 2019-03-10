package com.gxh.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Asset {
    @TableId
    private Integer assetId;

    private String assetUuid;

    private String assetType;

    private Integer assetStatus;

    private String taggerNumber;

    private String beijingCode;

    private String financeCode;

    private String serialNumber;

    private String computerModel;

    private Integer assetClass;

    private String assetPeople;

    private String assetQrcodeAddress;

}
