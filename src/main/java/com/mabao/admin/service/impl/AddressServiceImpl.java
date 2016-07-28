package com.mabao.admin.service.impl;

import com.mabao.admin.controller.vo.AddressVO;
import com.mabao.admin.pojo.Address;
import com.mabao.admin.pojo.User;
import com.mabao.admin.repository.AddressRepository;
import com.mabao.admin.service.AddressService;
import com.mabao.admin.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地址业务
 * Created by jackie on 2016/07/06.
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AreaService areaService;

    /**
     * 新增收货地址
     * @param address           地址对象
     * @return                  新增地址对象
     */
    @Override
    public Address addAddress(AddressVO address){
        return null;
    }

    /**
     * 更改选中收货地址
     * @param address           地址对象
     * @return                  修改的地址对象
     */
    @Override
    public Address updateAddress(Address address){
        return null;
    }

    /**
     * 删除收货地址
     * @param addressId         地址ID
     */
    @Override
    public void deleteAddress(Long addressId){
        this.addressRepository.delete(addressId);
    }
    /**
     * 依据ID获取地址
     * @param addressId         地址id
     * @return                  地址对象
     */
    @Override
    public Address get(Long addressId) {
        return this.addressRepository.findOne(addressId);
    }

}
