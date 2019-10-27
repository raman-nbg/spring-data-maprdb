package com.mapr.springframework.data.maprdb.repository.support;

import com.mapr.springframework.data.maprdb.core.MapROperations;
import com.mapr.springframework.data.maprdb.repository.MapRRepository;
import com.mapr.springframework.data.maprdb.repository.query.QueryUtils;
import org.ojai.store.Query;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SimpleMapRRepository<T, ID> implements MapRRepository<T, ID> {

    private final MapROperations maprOperations;
    private final Class<T> domainClass;

    public SimpleMapRRepository(final MapROperations maprOperations, final Class<T> domainClass) {
        super();
        this.maprOperations = maprOperations;
        this.domainClass = domainClass;

        if (maprOperations.isAutoCreateTablesEnabled() && !maprOperations.tableExists(domainClass)) {
            maprOperations.createTable(domainClass);
        }
    }

    @Override
    public <S extends T> S save(S entity) {
        return maprOperations.save(entity);
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return maprOperations.save(entities);
    }

    @Override
    public Optional<T> findById(final ID id) {
        return maprOperations.findById(id, domainClass);
    }

    @Override
    public boolean existsById(final ID id) {
        return findById(id).isPresent();
    }

    @Override
    public List<T> findAll() {
        return maprOperations.findAll(domainClass);
    }

    @Override
    public <S extends T> S insert(S entity) {
        return maprOperations.insert(entity);
    }

    @Override
    public <S extends T> List<S> insert(Iterable<S> entities) {
        return maprOperations.insert(entities);
    }

    @Override
    public List<T> findAllById(Iterable<ID> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .map(this::findById).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return maprOperations.count(domainClass);
        //throw new UnsupportedOperationException("count method is not supported yet");
    }

    @Override
    public void deleteById(final ID id) {
        maprOperations.removeById(id, domainClass);
    }

    @Override
    public void delete(Object entity) {
        maprOperations.remove(entity);
    }

    @Override
    public void deleteAll(Iterable entities) {
        maprOperations.remove(entities);
    }

    @Override
    public void deleteAll() {
        maprOperations.removeAll(domainClass);
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        throw new UnsupportedOperationException("findOne method with Example is not supported yet");
    }

    @Override
    public <S extends T> Iterable<S> findAll(Example<S> example) {
        throw new UnsupportedOperationException("findOne method with Example is not supported yet");
    }

    @Override
    public <S extends T> Iterable<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException("findAll method with Example and Sort is not supported yet");
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationException("findAll method with Example and Pageable is not supported yet");
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        throw new UnsupportedOperationException("count method with Example is not supported yet");
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        throw new UnsupportedOperationException("exists method with Example is not supported yet");
    }

    @Override
    public List<T> findAll(Sort sort) {
        Query query = maprOperations.getConnection().newQuery();

        QueryUtils.addSortToQuery(query, sort);

        return maprOperations.execute(query.build(), domainClass);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        long count = maprOperations.count(domainClass);

        Query query = maprOperations.getConnection().newQuery();
        QueryUtils.addPageableToQuery(query, pageable);

        List<T> list = maprOperations.execute(query.build(), domainClass);

        return new PageImpl<>(list, pageable, count);
    }
}
