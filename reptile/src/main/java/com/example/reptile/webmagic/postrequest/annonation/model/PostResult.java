package com.example.reptile.webmagic.postrequest.annonation.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/12 15:22
 */
@TargetUrl("https://msg.csdn.net/v1/web/message/view/unread")
@NoArgsConstructor
@Data
public class PostResult {

    @ExtractBy(value = "$.code", type = ExtractBy.Type.JsonPath, source = ExtractBy.Source.RawText)
    private String code;
    @ExtractBy(value = "$.message", type = ExtractBy.Type.JsonPath, source = ExtractBy.Source.RawText)
    private String message;
   /* @ExtractBy(value = "$.data", type = ExtractBy.Type.JsonPath, source = ExtractBy.Source.RawText)
    private DataDTO data;*/
    @ExtractBy(value = "$.status", type = ExtractBy.Type.JsonPath, source = ExtractBy.Source.RawText)
    private Boolean status;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JSONField(name = "thumb_up")
        private Integer thumbUp;
        @JSONField(name = "im")
        private Integer im;
        @JSONField(name = "gitChat_thumb_up")
        private Integer gitchatThumbUp;
        @JSONField(name = "avatarUrl")
        private String avatarUrl;
        @JSONField(name = "invitation")
        private Integer invitation;
        @JSONField(name = "gitChat_system")
        private Integer gitchatSystem;
        @JSONField(name = "GlobalSwitch")
        private GlobalSwitchDTO globalSwitch;
        @JSONField(name = "edu_thumb_up")
        private Integer eduThumbUp;
        @JSONField(name = "blink_thumb_up")
        private Integer blinkThumbUp;
        @JSONField(name = "follow")
        private Integer follow;
        @JSONField(name = "totalCount")
        private Integer totalCount;
        @JSONField(name = "coupon_order")
        private Integer couponOrder;
        @JSONField(name = "edu_comment")
        private Integer eduComment;
        @JSONField(name = "edu_system")
        private Integer eduSystem;
        @JSONField(name = "system")
        private Integer system;
        @JSONField(name = "comment")
        private Integer comment;
        @JSONField(name = "blink_comment")
        private Integer blinkComment;
        @JSONField(name = "gitChat_comment")
        private Integer gitchatComment;

        @NoArgsConstructor
        @Data
        public static class GlobalSwitchDTO {
            @JSONField(name = "private_message_who_follows_me")
            private Boolean privateMessageWhoFollowsMe;
            @JSONField(name = "email_commit_receive")
            private Boolean emailCommitReceive;
            @JSONField(name = "interactive_follow")
            private Boolean interactiveFollow;
            @JSONField(name = "email_collect_receive")
            private Boolean emailCollectReceive;
            @JSONField(name = "interactive_notifications")
            private Boolean interactiveNotifications;
            @JSONField(name = "system_digital")
            private Boolean systemDigital;
            @JSONField(name = "private_message_stranger")
            private Boolean privateMessageStranger;
            @JSONField(name = "email_support_receive")
            private Boolean emailSupportReceive;
            @JSONField(name = "interactive_like")
            private Boolean interactiveLike;
            @JSONField(name = "interactive_comment_digital")
            private Boolean interactiveCommentDigital;
            @JSONField(name = "system")
            private Boolean system;
            @JSONField(name = "user_follow")
            private Boolean userFollow;
            @JSONField(name = "interactive_like_digital")
            private Boolean interactiveLikeDigital;
            @JSONField(name = "comment_notifications")
            private Boolean commentNotifications;
            @JSONField(name = "interactive_follow_digital")
            private Boolean interactiveFollowDigital;
            @JSONField(name = "email_follow_receive")
            private Boolean emailFollowReceive;
            @JSONField(name = "interactive_comment")
            private Boolean interactiveComment;
            @JSONField(name = "private_message_who_me_follows")
            private Boolean privateMessageWhoMeFollows;
            @JSONField(name = "announcement_digital")
            private Boolean announcementDigital;
            @JSONField(name = "email")
            private Boolean email;
            @JSONField(name = "announcement")
            private Boolean announcement;
        }
    }
}
