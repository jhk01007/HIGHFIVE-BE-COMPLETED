package advancedweb.project.boardservice.global.paging;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.*;

import java.util.Iterator;
import java.util.List;

/**
 * Jackson-friendly한 Page 구현체.
 */
public class MyPage<T> implements Page<T> {

    private final List<T> content;
    private final Pageable pageable;
    private final long total;

    private final PageImpl<T> delegate;  // 실제 로직은 PageImpl에 위임

    @JsonCreator
    public MyPage(
        @JsonProperty("content") List<T> content,
        @JsonProperty("pageable") Pageable pageable,
        @JsonProperty("total") long total
    ) {
        this.content = content;
        this.pageable = pageable;
        this.total = total;
        this.delegate = new PageImpl<>(content, pageable, total);
    }

    @Override public int getTotalPages()     { return delegate.getTotalPages(); }
    @Override public long getTotalElements() { return delegate.getTotalElements(); }
    @Override public <U> Page<U> map(java.util.function.Function<? super T, ? extends U> converter) {
        return delegate.map(converter);
    }
    @Override public int getNumber()         { return delegate.getNumber(); }
    @Override public int getSize()           { return delegate.getSize(); }
    @Override public int getNumberOfElements(){return delegate.getNumberOfElements();}
    @Override public List<T> getContent()    { return delegate.getContent(); }
    @Override public boolean hasContent()    { return delegate.hasContent(); }
    @Override public Sort getSort()          { return delegate.getSort(); }
    @Override public boolean isFirst()       { return delegate.isFirst(); }
    @Override public boolean isLast()        { return delegate.isLast(); }
    @Override public boolean hasNext()       { return delegate.hasNext(); }
    @Override public boolean hasPrevious()   { return delegate.hasPrevious(); }
    @Override public Pageable nextPageable(){ return delegate.nextPageable(); }
    @Override public Pageable previousPageable(){return delegate.previousPageable();}
    @Override public Iterator<T> iterator() { return delegate.iterator(); }
}
