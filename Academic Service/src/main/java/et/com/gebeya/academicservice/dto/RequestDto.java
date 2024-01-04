package et.com.gebeya.academicservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class RequestDto {
    private List<SearchRequestDto> searchRequestDto;
    private GlobalOperator globalOperator;
    private PageDto pageRequestDto;

    public enum GlobalOperator{
        OR,AND
    }
}
