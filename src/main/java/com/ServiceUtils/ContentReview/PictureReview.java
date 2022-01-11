package com.ServiceUtils.ContentReview;
import com.alibaba.fastjson.JSON;
import com.huaweicloud.sdk.core.auth.BasicCredentials;
import com.huaweicloud.sdk.core.auth.ICredential;
import com.huaweicloud.sdk.core.exception.ConnectionException;
import com.huaweicloud.sdk.core.exception.RequestTimeoutException;
import com.huaweicloud.sdk.core.exception.ServiceResponseException;
import com.huaweicloud.sdk.moderation.v2.ModerationClient;
import com.huaweicloud.sdk.moderation.v2.model.ImageDetectionReq;
import com.huaweicloud.sdk.moderation.v2.model.RunImageModerationRequest;
import com.huaweicloud.sdk.moderation.v2.model.RunImageModerationResponse;
import com.huaweicloud.sdk.moderation.v2.region.ModerationRegion;

import java.util.ArrayList;
import java.util.List;

public class PictureReview {
    public static Object Review(byte[] image) {
        String ak = "2ZFFGROBNPJB9ZTMWFOE";
        String sk = "rvDA7QKymJqAvXkM9oeoYLt58G2kfp3hHfXqTl9B";
        ICredential auth = new BasicCredentials().withAk(ak).withSk(sk);
        ModerationClient client = ModerationClient.newBuilder().withCredential(auth).withRegion(ModerationRegion.valueOf("cn-north-4")).build();
        RunImageModerationRequest request = new RunImageModerationRequest();
        ImageDetectionReq body = new ImageDetectionReq();
        List<ImageDetectionReq.CategoriesEnum> listbodyCategories = new ArrayList<>();
        listbodyCategories.add(ImageDetectionReq.CategoriesEnum.fromValue("politics"));
        listbodyCategories.add(ImageDetectionReq.CategoriesEnum.fromValue("terrorism"));
        listbodyCategories.add(ImageDetectionReq.CategoriesEnum.fromValue("porn"));
        body.withCategories(listbodyCategories);
        body.withModerationRule("default");
//        body.withUrl("https://sdk-obs-source-save.obs.cn-north-4.myhuaweicloud.com/terrorism.jpg");
        body.withImage(image);
        request.withBody(body);

        RunImageModerationResponse response = null;
        try {
             response = client.runImageModeration(request);
            System.out.println(response.getHttpStatusCode());
            System.out.println(JSON.toJSONString(response));


        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (RequestTimeoutException e) {
            e.printStackTrace();
        } catch (ServiceResponseException e) {
            e.printStackTrace();
            System.out.println(e.getHttpStatusCode());
            System.out.println(e.getErrorCode());
            System.out.println(e.getErrorMsg());
        }
        return JSON.toJSON(response);
    }
}



