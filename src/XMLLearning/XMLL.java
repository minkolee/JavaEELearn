package XMLLearning;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;


/**
 * DTD之类的都是一种约束,用来规定文档怎么写
 * struts 2 , hibernate 使用DTD约束
 * DTD其实每一行就是元素与其中的子元素的数量,类似正则的方式表达
 *
 * Schema 是 DTD 的替代者
 * Spring框架使用Schema约束XML文档
 *
 * Schema 的声明不用自己写, 实际从框架导入可用的命名空间
 *
 *
 */

public class XMLL {

    public static void main(String[] args) throws Exception {
        SAXReader reader = new SAXReader();
        File file = new File("d:\\dom.xml");
        Document dom = reader.read(file);
        System.out.println(dom);

//        获取根元素
//        获取某个元素的属性值
        Element root = dom.getRootElement();
        String value = root.attributeValue("version");
        System.out.println(value);


        //获取所有子元素
        List<Element> allChild = root.elements();

        System.out.println(allChild.get(0).element("title").getText());

    }

}
