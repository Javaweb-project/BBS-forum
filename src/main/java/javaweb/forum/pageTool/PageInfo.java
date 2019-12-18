package javaweb.forum.pageTool;
import java.util.List;

public class PageInfo {
    private int TotalPage;
    private int PageSize;
    private int PageNow;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean havePerPage;
    private boolean haveNexPage;
    private List<?> list;

    public PageInfo() {
        // TODO 自动生成的构造函数存根
    }

    public int getTotalPage() {
        return TotalPage;
    }

    public void setTotalPage(int totlePage) {
        TotalPage = totlePage;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public int getPageNow() {
        return PageNow;
    }

    public void setPageNow(int pageNow) {
        PageNow = pageNow;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean isHavePerPage() {
        return havePerPage;
    }

    public void setHavePerPage(boolean havePerPage) {
        this.havePerPage = havePerPage;
    }

    public boolean isHaveNexPage() {
        return haveNexPage;
    }

    public void setHaveNexPage(boolean haveNexPage) {
        this.haveNexPage = haveNexPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

}
