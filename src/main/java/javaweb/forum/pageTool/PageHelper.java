package javaweb.forum.pageTool;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页插件
 *
 * @author XinYutian
 *
 */
public class PageHelper {
    public List<PageInfo> SetStartPage(List<?> list,int pageNow,int Size){
        boolean isFirstPage=false;
        boolean isLastPage=false;
        boolean haveNexPage=false;
        boolean havePerPage=false;
        int pageSize=0;
        int fromIndex=(pageNow-1)*Size;
        int toIndex=pageNow*Size;
        if(fromIndex==0) {
            isFirstPage=true;
        }else if (!isFirstPage) {
            havePerPage=true;
        }
        if(toIndex>=list.size()) {
            toIndex=list.size();
            isLastPage=true;
        }else if (!isLastPage) {
            haveNexPage=true;
        }
        if(list.size()%Size==0) {
            pageSize=list.size()/Size;
        }else {
            pageSize=list.size()/Size+1;
        }
        List<PageInfo> pageInfos=new ArrayList<>();
        PageInfo pageInfo=new PageInfo();
        pageInfo.setPageNow(pageNow);
        pageInfo.setTotalPage(pageSize);
        pageInfo.setPageSize(Size);
        pageInfo.setFirstPage(isFirstPage);
        pageInfo.setLastPage(isLastPage);
        pageInfo.setHaveNexPage(haveNexPage);
        pageInfo.setHavePerPage(havePerPage);
        pageInfo.setList(list.subList(fromIndex, toIndex));
        pageInfos.add(pageInfo);
        return pageInfos;
    }
}
