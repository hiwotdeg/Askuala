package et.com.gebeya.academicservice.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@Data
public class PageDto {
    private Integer pageNo = 0;
    private Integer pageSize = 10;
    private Sort.Direction sort = Sort.Direction.ASC;
    private String sortByColumn = "id";

    public Pageable getPageable(PageDto pageDto){
      Integer page = Objects.nonNull(pageDto.getPageNo())?pageDto.getPageNo():this.pageNo;
      Integer size = Objects.nonNull(pageDto.getPageSize())?pageDto.getPageSize():this.pageSize;
      Sort.Direction sort = Objects.nonNull(pageDto.getSort())?pageDto.getSort():this.sort;
      String sortByColumn = Objects.nonNull(pageDto.getSortByColumn())?pageDto.getSortByColumn():this.sortByColumn;

      PageRequest pageRequest;
      pageRequest = PageRequest.of(page,size,sort,sortByColumn);
      return  pageRequest;
    }
}
