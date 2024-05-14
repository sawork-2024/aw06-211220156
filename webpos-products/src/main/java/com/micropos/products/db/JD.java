package com.micropos.products.db;
import com.micropos.products.jpa.ProductRepository;
import com.micropos.products.model.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class JD implements com.micropos.products.db.ProductDB {
    @Autowired
    private ProductRepository productRepository;
    private boolean init = false;

    @Override
    public List<Product> getProducts() {
        try {
            if (!init) {
                parseJD("Java");
                init = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        throw new RuntimeException("故意的");
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(String productId) {
        for (Product p : getProducts()) {
            if (p.getId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

    public void parseJD(String keyword) throws IOException {
        //获取请求https://search.jd.com/Search?keyword=java
        System.out.println("hello");
        String url = "https://search.jd.com/Search?keyword=" + keyword;
        //解析网页
//        Document document = Jsoup.parse(new URL(url), 10000);
        Document document = Jsoup.connect(url).cookie("thor", "EFAD41B91D876362F624366C2ACCEE3F9A18204C7123DE164E42AAEF5E6C3C34B3FB11CB0EFC5FB3A90A7F69BC0687671D1A7029FB919B168A2BA2A3EED57A768C544360A332B4B66D9ABF9FDC9B6DBE03C0EE1CFCCF5756B533CEF3BA82FBFAA4479F640CE19AF31AC67E592EAF225C4BFD00164A7D83D2354FCDA24CFF6D1D0084F547C2DBE450267BC4D8DC2B394E").get();
        //所有js的方法都能用
        Element element = document.getElementById("J_goodsList");
        //获取所有li标签
        Elements elements = element.getElementsByTag("li");
//        List<Product> list = new ArrayList<>();

        //获取元素的内容
        for (Element el : elements
        ) {
            try {
                //关于图片特别多的网站，所有图片都是延迟加载的
                String id = el.attr("data-spu");
                String img = "https:".concat(el.getElementsByTag("img").eq(0).attr("data-lazy-img"));
                String price = el.getElementsByAttribute("data-price").text();
                String title = el.getElementsByClass("p-name").eq(0).text();
                if (title.indexOf("，") >= 0)
                    title = title.substring(0, title.indexOf("，"));
                if (id == "") continue;

                Product product = new Product(id, title, Double.parseDouble(price), img);

                productRepository.save(product);
            } catch (NumberFormatException ignored) {
            }
        }
    }
    @Override
    public List<Product> getProductsByKeyword(String keyword) {
        return null;
    }

    @Override
    public void updateProduct(String productId, int quantity) {
        productRepository.updateProductQuantity(productId, quantity);
    }

}
