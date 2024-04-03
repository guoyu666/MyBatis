package xml.test;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class ParseXMLByDom4jTest {
    @Test
    public void testParseSqlMapperXML() throws Exception {
        SAXReader reader = new SAXReader();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("CarMapper.xml");
        Document document = reader.read(is);

        // 获取namespace
        String xpath = "/mapper";
        Element mapper = (Element) document.selectSingleNode(xpath);
        String namespace = mapper.attributeValue("namespace");
        System.out.println(namespace);

        // 获取mapper节点下的所有子节点
        for (Element element : mapper.elements()) {
            String id = element.attributeValue("id");
            System.out.println(id);
            // 获取resultType
            String resultType = element.attributeValue("resultType");
            System.out.println(resultType); // 没有这个属性的话，会自动返回null
            // 获取标签中sql语句（标记获取标签中的文本内容，而且去除前后空白）
            String sql = element.getTextTrim();
            System.out.println(sql);
        }
    }

    @Test
    public void testParseXMLByDom4j() throws Exception {
        // 创建SAXReader对象
        SAXReader reader = new SAXReader();
        // 获取输入流
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");
        // 读取xml文件，返回document对象,document对象是文档对象，代表了整个xml文件
        Document document = reader.read(is);
        // 获取文档中的根标签
        Element rootElement = document.getRootElement();
        String rootElementName = rootElement.getName();
        System.out.println("根节点的名字: " + rootElementName);

        // 获取的default默认的环境ID
        // xpath是做标签路径匹配的，能够让我们快速定位xml文件中的元素
        // 以下的xpath代表了：从根下开始找configuration标签，然后找configuration标签下的environments
        String xpath = "/configuration/environments";
        Element environments = (Element) document.selectSingleNode(xpath); // Element是Node类的子类，方法更多，使用更加便捷
        // 获取属性的值
        String defaultEnvironmentId = environments.attributeValue("default");
        System.out.println("默认环境的id: " + defaultEnvironmentId);

        // 获取具体的环境environment
        xpath = "/configuration/environments/environment[@id='" + defaultEnvironmentId + "']";
        Element environment = (Element) document.selectSingleNode(xpath);

        // 获取environment节点下的transactionManager节点(Element的element()方法用来获取孩子节点)
        Element transactionManager = environment.element("transactionManager");
        String transactionType = transactionManager.attributeValue("type");
        System.out.println("事务管理器的类型：" + transactionType);

        // 获取dataSource节点
        Element dataSource = environment.element("dataSource");
        String dataSourceType = dataSource.attributeValue("type");
        System.out.println("数据源的类型：" + dataSourceType);

        // 获取dataSource节点下的所有子节点
        for (Element element : dataSource.elements()) {
            System.out.println(element.attributeValue("name"));
        }
        // 获取所有的mapper标签
        // 不想从根下开始获取，你想从任意位置开始，获取所有的某个标签，xpath应该这样写
        xpath = "//mapper";
        List<Node> mappers = document.selectNodes(xpath);
        // 遍历
        mappers.forEach(mapper -> {
            Element mapperElt = (Element) mapper;
            String mapperName = mapperElt.attributeValue("resource");
            System.out.println(mapperName);
        });
    }
}
