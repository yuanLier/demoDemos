package GsonDemo;

import java.util.List;

public class GsonTitleBean {
    //只有花括号时定义类型为内部类
    public DataInfo data;

    //内部类使用static:防止内存泄漏
    public static class DataInfo{
        //既有花括号又有中括号时定义类型为List<内部类>
        public List<TranslationInfo> translations;

        public static class TranslationInfo{
            public String title;
            public QuestionInfo question;

            public static class QuestionInfo{
                public int questionId;
            }
        }
    }
}
