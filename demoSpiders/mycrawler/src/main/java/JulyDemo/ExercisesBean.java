package JulyDemo;

import java.util.List;

public class ExercisesBean {
    //题号（id）
    private long id;
    //题目内容（content）
    private String ques;
    //题目分析（analyse）
    private String analysis;
    //选项相关（隶属于content）
    private List<OptionInfo> options;
    //？？？
    private int is_coll;

    //内部类使用static：防止内存泄漏
    public static class OptionInfo{
        //选项id
        private long opt_id;
        //选项分析
        private String des;
        //是否为答案
        private int is_ans;

        public long getOpt_id() {
            return opt_id;
        }

        public void setOpt_id(long opt_id) {
            this.opt_id = opt_id;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getIs_ans() {
            return is_ans;
        }

        public void setIs_ans(int is_ans) {
            this.is_ans = is_ans;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public List<OptionInfo> getOptions() {
        return options;
    }

    public void setOptions(List<OptionInfo> options) {
        this.options = options;
    }

    public int getIs_coll() {
        return is_coll;
    }

    public void setIs_coll(int is_coll) {
        this.is_coll = is_coll;
    }
}
