package com.mosaic.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@DiscriminatorValue("BALANCED_GREEDY")
public class BalancedGreedyCut extends Cut
{

}
