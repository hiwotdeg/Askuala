package et.com.gebeya.academicservice.service;

import et.com.gebeya.academicservice.dto.RequestDto;
import et.com.gebeya.academicservice.dto.SearchRequestDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SpecificationService<T> {

    public Specification<T> getSearchSpecification(SearchRequestDto searchRequestDto ){
        return  new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(searchRequestDto.getColumn()),searchRequestDto.getValue());
            }
        };
    }
    public Specification<T> getSearchSpecification(List<SearchRequestDto> searchRequestDtos , RequestDto.GlobalOperator globalOperator){
        return  new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
               List<Predicate> predicates = new ArrayList<>();
                //return criteriaBuilder.equal(root.get(searchRequestDto.getColumn()),searchRequestDto.getValue());

                for(SearchRequestDto searchRequestDto : searchRequestDtos) {
                    switch (searchRequestDto.getOperation()) {
                        case EQUAL:
                           Predicate equal = criteriaBuilder.equal(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
                           predicates.add(equal);
                           break;
                        case LIKE:
                            Predicate like = criteriaBuilder.like(root.get(searchRequestDto.getColumn()), "%"+searchRequestDto.getValue()+"%");
                            predicates.add(like);
                            break;
                        case IN:
                            String[] splitValue = searchRequestDto.getValue().split(",");
                            Predicate in = root.get(searchRequestDto.getColumn()).in(Arrays.asList(splitValue));
                            // Predicate in criteriaBuilder.in(root.get(searchRequestDto.getColumn()),spliteValue[0],spli
                            predicates.add(in);
                            break;
                        case GREATER_THAN:
                            Predicate greaterThan = criteriaBuilder.greaterThan(root.get(searchRequestDto.getColumn()),searchRequestDto.getValue());
                            predicates.add(greaterThan);
                            break;
                        case LESS_THAN:
                            Predicate lessThan = criteriaBuilder.lessThan(root.get(searchRequestDto.getColumn()),searchRequestDto.getValue());
                            predicates.add(lessThan);
                            break;
                        case BETWEEN:
                            String[]  split2 = searchRequestDto.getValue().split(",");
                            Predicate between = criteriaBuilder.between(root.get(searchRequestDto.getColumn()),Long.parseLong(split2[0]),Long.parseLong(split2[1]));
                            predicates.add(between);
                            break;
                        case JOIN:
                            Predicate join = criteriaBuilder.equal(root.join(searchRequestDto.getJoinTable()).get(searchRequestDto.getColumn()),searchRequestDto.getValue());
                            predicates.add(join);
                            break;
                        default:
                            throw new IllegalArgumentException("invalid  value");
                    }
                }
                if(globalOperator.equals(RequestDto.GlobalOperator.AND)){
                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                }
                else {
                    return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
                }

            }
        };
    }

}
