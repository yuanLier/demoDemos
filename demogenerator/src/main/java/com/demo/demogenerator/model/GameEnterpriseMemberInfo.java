package com.demo.demogenerator.model;

public class GameEnterpriseMemberInfo {
    private Long id;

    private Long gameId;

    private Long gameEnterpriseId;

    private Long userId;

    private String gameEnterpriseRole;

    private Integer gameContributionRate;

    private String gameExperience;

    public GameEnterpriseMemberInfo(Long id, Long gameId, Long gameEnterpriseId, Long userId, String gameEnterpriseRole, Integer gameContributionRate, String gameExperience) {
        this.id = id;
        this.gameId = gameId;
        this.gameEnterpriseId = gameEnterpriseId;
        this.userId = userId;
        this.gameEnterpriseRole = gameEnterpriseRole;
        this.gameContributionRate = gameContributionRate;
        this.gameExperience = gameExperience;
    }

    public GameEnterpriseMemberInfo() {
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

    public Long getGameEnterpriseId() {
        return gameEnterpriseId;
    }

    public void setGameEnterpriseId(Long gameEnterpriseId) {
        this.gameEnterpriseId = gameEnterpriseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getGameEnterpriseRole() {
        return gameEnterpriseRole;
    }

    public void setGameEnterpriseRole(String gameEnterpriseRole) {
        this.gameEnterpriseRole = gameEnterpriseRole == null ? null : gameEnterpriseRole.trim();
    }

    public Integer getGameContributionRate() {
        return gameContributionRate;
    }

    public void setGameContributionRate(Integer gameContributionRate) {
        this.gameContributionRate = gameContributionRate;
    }

    public String getGameExperience() {
        return gameExperience;
    }

    public void setGameExperience(String gameExperience) {
        this.gameExperience = gameExperience == null ? null : gameExperience.trim();
    }
}