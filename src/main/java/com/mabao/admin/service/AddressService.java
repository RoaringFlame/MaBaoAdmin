package com.mabao.admin.service;


import com.mabao.admin.controller.vo.AddressVO;
import com.mabao.admin.pojo.Address;

import java.util.List;

/**
 * 地址业务接口
 * Created by jackie on 2016/07/06.
 */
public interface AddressService {

    /**
     * 新增收货地址
     * @param address           地址对象
     * @return                  新增的地址对象
     */
    Address addAddress(AddressVO address);
    /**
     * 更改选中收货地址
     * @param address           地址对象
     * @return                  地址对象
     */
    Address updateAddress(Address address);
    /**
     * 删除收货地址
     * @param addressId         地址id
     */
    void deleteAddress(Long addressId);

    /**
     * 依据ID获取地址
     * @param addressId         地址id
     * @return                  地址对象
     */
    Address get(Long addressId);

}
