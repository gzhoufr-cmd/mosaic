package com.mosaic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mosaic.domain.Cut;

@Repository
public interface CutRepository extends CrudRepository<Cut, Long>
{

}
