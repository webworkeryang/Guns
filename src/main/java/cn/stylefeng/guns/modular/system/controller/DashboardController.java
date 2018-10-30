/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.modular.system.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.stylefeng.guns.core.common.node.MenuNode;
import cn.stylefeng.guns.core.util.ApiMenuFilter;
import cn.stylefeng.guns.modular.system.service.IMenuService;
import cn.stylefeng.guns.modular.system.service.INoticeService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * 总览信息
 *
 * @author fengshuonan
 * @Date 2017年3月4日23:05:54
 */
@Controller
@RequestMapping("/blackboard")
public class DashboardController extends BaseController {

    @Autowired
    private INoticeService noticeService;

    @Autowired
    private IMenuService menuService;

    /**
     * 跳转到黑板
     */
    @RequestMapping("")
    public String blackboard(Model model) {
        List<Map<String, Object>> notices = noticeService.list(null);
        model.addAttribute("noticeList", notices);

        //获取菜单列表
        List<MenuNode> tempMenus = menuService.getMenusByRoleIds(CollectionUtil.newArrayList(1));
        List<MenuNode> menus = MenuNode.buildTitle(tempMenus);
        menus = ApiMenuFilter.build(menus);

        model.addAttribute("menus", menus);

        //获取用户头像
        model.addAttribute("name", "stylefeng");
        model.addAttribute("avatar", "/assets/images/users/1.jpg");
        model.addAttribute("email", "sn93@qq.com");

        return "/dashboard.html";
    }
}
