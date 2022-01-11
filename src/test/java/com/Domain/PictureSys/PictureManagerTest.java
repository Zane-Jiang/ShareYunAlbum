package com.Domain.PictureSys;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

public class PictureManagerTest extends TestCase {

    @Test
    public void testGetAllPicture() {
        ArrayList<String> picId = PictureManager.getAllPicture();
        if(picId == null){
            System.out.println("aaaaa");
        }
        System.out.println(picId.size());
        for(String pic :picId){
            System.out.println(pic);
        }
    }
}