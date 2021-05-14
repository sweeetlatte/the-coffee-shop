package com.quintus.labs.grocerystore.helper;

import com.quintus.labs.grocerystore.model.Offer;

import java.util.ArrayList;
import java.util.List;


public class Data {

    List<Offer> offerList = new ArrayList<>();


    public List<Offer> getOfferList() {
        Offer offer = new Offer("https://cdn.dayphache.edu.vn/wp-content/uploads/2019/05/hinh-tao-hinh-3d-ca-pha-latte.jpg");
        offerList.add(offer);
        offer = new Offer("https://www.takadada.com/wp-content/uploads/2019/07/hinh-anh-ly-ca-phe-48.jpg");
        offerList.add(offer);
        offer = new Offer("https://cdn.dayphache.edu.vn/wp-content/uploads/2019/01/do-uong-nao-se-thong-tri-thi-truong-nam-2021.jpg");
        offerList.add(offer);
        offer = new Offer("https://tred.vn/wp-content/uploads/2019/12/DSC09789.jpg");
        offerList.add(offer);
        offer = new Offer("https://tred.vn/wp-content/uploads/2019/12/DSC00117-copy.jpg");
        offerList.add(offer);


        return offerList;
    }
}
