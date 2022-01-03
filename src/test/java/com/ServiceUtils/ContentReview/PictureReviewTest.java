package com.ServiceUtils.ContentReview;

import com.Domain.PictureSys.PictureManager;
import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class PictureReviewTest extends TestCase {
    @Test
  public void testPicReview(){
    InputStream pic = PictureManager.download("1234562021121114314920220103191629");
      try {
           byte[] picByte =IOUtils.toByteArray(pic);
          PictureReview.Review(picByte);
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

}