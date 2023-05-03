package Gongbok_BE.Gongbok.auth.oauth2.userinfo;

import java.util.Map;

public class AppleOAuth2UserInfo extends OAuth2UserInfo {

    // TODO: Apple 로그인 사용을 위한 설정 필요
    public AppleOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getNickname() {
        Map<String, Object> account = getAppleAccount(attributes, "kakao_account");
        Map<String, Object> profile = getAppleAccount(account, "profile");

        if (account == null || profile == null) {
            return null;
        }

        return (String) profile.get("nickname");
    }
    
    @Override
    public String getImageUrl() {
        Map<String, Object> account = getAppleAccount(attributes, "kakao_account");
        Map<String, Object> profile = getAppleAccount(account, "profile");

        if (account == null || profile == null) {
            return null;
        }

        return (String) profile.get("thumbnail_image_url");
    }

    private Map<String, Object> getAppleAccount(Map<String, Object> attributes, String appleAccount) {
        return (Map<String, Object>) attributes.get(appleAccount);
    }
}
