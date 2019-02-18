package com.demo.demogenerator.model;

public class GameEnterpriseInfo {
    private Long id;

    private Long gameId;

    private Long enterpriseCeo;

    private Integer enterpriseCurrentPeriod;

    private Integer enterpriseMemberNumber;

    private Byte enterpriseStatus;

    public GameEnterpriseInfo(Long id, Long gameId, Long enterpriseCeo, Integer enterpriseCurrentPeriod, Integer enterpriseMemberNumber, Byte enterpriseStatus) {
        this.id = id;
        this.gameId = gameId;
        this.enterpriseCeo = enterpriseCeo;
        this.enterpriseCurrentPeriod = enterpriseCurrentPeriod;
        this.enterpriseMemberNumber = enterpriseMemberNumber;
        this.enterpriseStatus = enterpriseStatus;
    }

    public GameEnterpriseInfo() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getEnterpriseCeo() {
        return enterpriseCeo;
    }

    public void setEnterpriseCeo(Long enterpriseCeo) {
        this.enterpriseCeo = enterpriseCeo;
    }

    public Integer getEnterpriseCurrentPeriod() {
        return enterpriseCurrentPeriod;
    }

    public void setEnterpriseCurrentPeriod(Integer enterpriseCurrentPeriod) {
        this.enterpriseCurrentPeriod = enterpriseCurrentPeriod;
    }

    public Integer getEnterpriseMemberNumber() {
        return enterpriseMemberNumber;
    }

    public void setEnterpriseMemberNumber(Integer enterpriseMemberNumber) {
        this.enterpriseMemberNumber = enterpriseMemberNumber;
    }

    public Byte getEnterpriseStatus() {
        return enterpriseStatus;
    }

    public void setEnterpriseStatus(Byte enterpriseStatus) {
        this.enterpriseStatus = enterpriseStatus;
    }
}