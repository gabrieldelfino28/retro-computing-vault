package br.gov.sp.fateczl.museu.util.service;

import br.gov.sp.fateczl.museu.exception.BusinessRuleException;
import br.gov.sp.fateczl.museu.exception.codes.NullErr;
import br.gov.sp.fateczl.museu.util.FluentValidator;
import br.gov.sp.fateczl.museu.util.Logger;
import br.gov.sp.fateczl.museu.util.logging.MuseumLogger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ServiceSupport {

    default Logger log() { return MuseumLogger.of(this.getClass()); }

    default String entity() {
        return getClass().getSimpleName()
                .replace("Template", "")
                .replace("ServiceImpl", "")
                .replace("Service", "");
    }

    default <E> String collection(Collection<E> collection) {
        if (collection == null || collection.isEmpty()) return "Unknown";
        String name = collection.iterator().next().getClass().getSimpleName();
        return collection.size() > 1 ? name + "s" : name;
    }

    default <E> E orElseNotFound(Optional<E> optional) {
        return optional.orElseThrow(() -> new BusinessRuleException(NullErr.NOT_FOUND));
    }

    default <E> Collection<E> orElseNotFound(Collection<E> collection) {
        FluentValidator.begin().check(collection == null|| collection.isEmpty(), NullErr.NOT_FOUND);
        return collection;
    }

    //Previously known as CheckEmptyList!
    default <E> List<E> orElseNotFound(List<E> list) {
        FluentValidator.begin().check(list == null|| list.isEmpty(), NullErr.NOT_FOUND);
        return list;
    }

    //In case of switching to Pages in the Future
    default Pageable defaultPageable(int page, int size) {
        return PageRequest.of(
                Math.max(page, 0),
                size > 0 && size <= 50 ? size : 20
        );
    }

    default <E> Page<E> toPage(List<E> list, Pageable pageable) {
        if (list == null || list.isEmpty()) return Page.empty(pageable);

        int start = (int) pageable.getOffset();
        int end   = Math.min(start + pageable.getPageSize(), list.size());

        if (start >= list.size()) return Page.empty(pageable);

        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }
}
