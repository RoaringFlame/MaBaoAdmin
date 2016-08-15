/**
 * Created by Emma on 2016/8/15.
 * 轮播时间设置
 */
"use strict";
$(function () {
    //设定轮播时间2秒
    function initCarousel() {
        $(function () {
            $('.carousel').carousel({
                interval: 2500
            })
        });
    }

    function init() {
        initCarousel();
    }

    init();
});
