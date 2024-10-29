package com.treizer.spring_boot_rest.service.interfaces;

import java.math.BigDecimal;
import java.util.List;

public interface IProductService<T, TI, TU> extends ICommonService<T, TI, TU> {

    List<T> findByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice);

}
