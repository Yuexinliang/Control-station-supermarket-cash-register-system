package com.myhd;

import com.myhd.exception.LoginException;
import com.myhd.exception.RegisterException;
import com.myhd.pojo.*;
import com.myhd.service.*;
import com.myhd.service.impl.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.BiConsumer;

/**
 * className Main
 * packageName com. Mod
 * Description 一级菜单
 *
 * @author CYQH
 * @email 1660855825@qq.com
 * @Date 2023/08/20 19:11
 * @version 1.0
 */
public class Main {
    static String AdministratorPassword = "root";
    static EmpUser empUser = new EmpUser();
    static Scanner input = new Scanner(System.in);
    /*用户*/
    static  IEmpUserService eus = new EmpUserServiceImpl();
    static {
        /*进入平台*/
        AccessPlatform();
    }
    public static void main(String[] args) {
    }
    /**
     * @description: 展示超市界面和一级菜单
     * @return: void
     * @author CYQH
     * @date: 2023/08/20 19:53
     */
    public static void AccessPlatform(){
        System.out.println("-------------------------------------------------------------");
        System.out.println("-***********************************************************-");
        System.out.println("-*    ⭐      ⭐       ⭐      ⭐      ⭐        ⭐       *-");
        System.out.println("-*     ⭐       ⭐      MYHD超市收银系统      ⭐       ⭐    *-");
        System.out.println("-* ⭐       ⭐        ⭐        ⭐      ⭐         ⭐     *-");
        System.out.println("-***********************************************************-");
        System.out.println("-------------------------------------------------------------");
        System.out.println("1.✒注册；2.⬆登录；0.🚪退出");
        System.out.print("请选择您的操作序号：");
        try {
            switch (input.nextInt()){
                case 1:register();break;
                case 2:Login();break;
                case 0:
                    System.out.println("                    欢迎下次使用                     ");
                    System.out.println("( ﾟдﾟ)つBye( ﾟдﾟ)つBye( ﾟдﾟ)つBye( ﾟдﾟ)つBye( ﾟдﾟ)つBye");
                    System.exit(0);
                default:
                    System.out.println("输入格式不规范，请重新输入");AccessPlatform();break;
            }
        }catch (InputMismatchException e){
            System.out.println("输入类型错误，请重新输入");
            /*清除输入缓冲区*/
            input.nextLine();
            AccessPlatform();
        }
    }
    /**
     * @description: 注册功能
     * @return: void
     * @author CYQH
     * @date: 2023/08/20 20:14
     */
    public static void register(){
        EmpUser empUser = new EmpUser();
        System.out.println("*************************欢迎来到注册界面************************");
        System.out.println("请输入你的姓名");
        empUser.setEmpUserName(input.next());
        while (true){
            System.out.println("请输入你的密码");
            String pwd1 = input.next();
            System.out.println("请再次输入你的密码");
            if (pwd1.equals(input.next())){
                empUser.setEmpUserPwd(pwd1);
                break;
            }else {
                System.out.println("两次输入的密码不一致，请重新！");
            }
        }
        try {
            System.out.println("请输入你的职位");
            String duty = input.next();
            if (duty.equals("总经理")){
                System.out.println("请输入管理员密码：");
                if (AdministratorPassword.equals(input.next())){
                    empUser.setEmpUserDuty(duty);
                        System.out.println("请设置用户id吗");
                            System.out.println("请输入你的用户id(仅为数字，五位以内)");
                            empUser.setEmpUserId(input.nextInt());
                    eus.RegisterService(empUser);
                }else {
                    throw new RegisterException("你无权注册总经理职位");
                }
            }else {
                empUser.setEmpUserDuty(duty);
                eus.RegisterService(empUser);
            }
        }catch (RegisterException e){
            e.printStackTrace();
        }finally {
            AccessPlatform();
        }
    }
    /**
     * @description: 登录功能
     * @return: void
     * @author CYQH
     * @date: 2023/08/20 20:41
     */
    public static void Login(){
        System.out.println("********************欢迎来到登录界面***********************");
        try {
            System.out.println("请输入你的用户id：");
            empUser.setEmpUserId(input.nextInt());
            System.out.println("请输入你的密码");
            empUser.setEmpUserPwd(input.next());
            try {
                if (eus.LoginService(empUser)){
                    EmpUser empUser1 = eus.QueryUser(empUser);
                    if (empUser1.getEmpUserDuty().equals("总经理")){
                        main2.enterManagerMenu();
                    }else {
                        main2.enterStaffMenu();
                    }
                }else {
                    throw new LoginException("登录失败，请检查用户id和密码");
                }
            }catch (LoginException e){
                e.printStackTrace();
            }
        }catch (InputMismatchException e){
            System.out.println("输入类型错误，请重新输入");
            /*清除输入缓冲区*/
            input.nextLine();
            Login();
        }
        AccessPlatform();
    }
}
/**
 * className main2
 * packageName com.myhd
 * Description  二级菜单
 *
 * @author CYQH
 * @email 1660855825@qq.com
 * @Date 2023/08/20 21:03
 * @version 1.0
 */
class main2{
    static Scanner input = new Scanner(System.in);
    /*活动*/
    static IActivityService as = new ActivityServiceImpl();
    /*产品*/
    static  IProductService ps = new ProductServiceImpl();
    /*账户*/
    static  IAccountService ias = new AccountServiceImpl();
    /*订单*/
    static IOrderService os = new OrderServiceImpl();
    /*订单项*/
    static IOrderItemService ois = new OrderItemServiceImpl();
    public static void enterStaffMenu(){
        System.out.println("***********************员工操作界面***************************");
        System.out.println("1.结账；2.查看商品列表；3.退货；4.查询活动；0.返回上一级菜单");
        System.out.print("请选择您的操作序号：");
         try {
             switch (input.nextInt()){
                 case 1:
                     Order order = new Order();
                     order.setOrderDateTime(LocalDateTime.now());
                     Integer orderId = os.InsertOrder(order);
                     order.setOrderId(orderId);
                     SettleAccounts(order);
                     break;
                 case 2:showAllProduct(); break;
                 case 3:returnOfGoods();  break;
                 case 4:empCheckActivity();  break;
                 case 0:Main.AccessPlatform();
             }
         }catch (InputMismatchException e){
             System.out.println("输入类型错误，请重新输入");
             /*清除输入缓冲区*/
             input.nextLine();
             enterStaffMenu();
         }

    }
    /**
     * @description: 展示商品所有信息
     * @return: void
     * @author CYQH
     * @date: 2023/08/20 23:47
     */
    public static void showAllProduct(){
        System.out.println("*********************商品列表************************");
        List<Product> products = ps.selectAllProduct();
        for (Product product : products) {
            System.out.println(product);
        }
        enterStaffMenu();
    }
    /**
     * @description: 结账操作，内包含添加商品和移除商品，最终结账
     * @return: void
     * @author CYQH
     * @date: 2023/08/21 8:52
     */
    public static void SettleAccounts(Order order){
        System.out.println("**********************结账界面**********************");
        HashMap<ArrayList<Integer>, OrderItem> hashMap = new HashMap<>();
        System.out.println("您的订单编号为："+order.getOrderId());
        try {
            while (true){
                System.out.println("1.添加商品；2.移除商品；0.返回上一级菜单");
                System.out.print("请选择您的操作序号：");
                String str = input.next();
                if ("1".equals(str)){
                    break;
                }else if ("2".equals(str)){
                    System.out.println("您的订单中暂无商品");
                }else if ("0".equals(str)){
                    enterStaffMenu();
                }else {
                    System.out.println("操作序号错误，请重新输入");
                }
            }
        outer1:
        while (true){
            try {
                int count = 0;
                ArrayList<Integer> id = new ArrayList<>();
                id.add(order.getOrderId());
                System.out.println("请录入产品id：");
                int productId = input.nextInt();
                id.add(productId);
                if(hashMap.containsKey(id)){
                    OrderItem orderItem = hashMap.get(id);
                    count = orderItem.getProductCount();
                }
                System.out.println("请录入产品数量：");
                int productCount = input.nextInt() + count;
                Product product = ps.selectByproductId(productId);
                OrderItem orderItem = new OrderItem(order.getOrderId(),productId,product.getProductName(),productCount,product.getProductPrice(),
                        BigDecimal.valueOf(productCount * product.getProductPrice()).setScale(2, RoundingMode.HALF_UP).doubleValue());
                hashMap.put(id, orderItem);
            }catch (NullPointerException e){
                System.out.println("产品id不存在");
            }
            printOrderList(hashMap);
            outer2:
            while (true){
                System.out.println("1.继续添加商品；2.移除商品；3.清空购物车；4.查看当前购物车；0.付款");
                System.out.print("请选择您的操作序号：");
                String str = input.next();
                if ("0".equals(str)){
                    break ;
                }else if ("2".equals(str)){
                        /*移除商品*/
                        int oldCount = 0;
                        ArrayList<Integer> id = new ArrayList<>();
                        id.add(order.getOrderId());
                        System.out.println("请输入要移除的产品id：");
                        int productId = input.nextInt();
                        id.add(productId);
                    try {
                        outer3:
                        while (true) {
                            System.out.println("请输入要移除的产品数量：");
                            int removeCount = input.nextInt();
                            if (hashMap.containsKey(id)) {
                                OrderItem orderItem = hashMap.get(id);
                                oldCount = orderItem.getProductCount();
                                if (removeCount > oldCount) {
                                    System.out.println("移除产品数量大于订单现有产品数量！");
                                    continue outer2;
                                }
                            }
                            int newCount = oldCount - removeCount;
                            Product product = ps.selectByproductId(productId);
                            OrderItem orderItem = new OrderItem(order.getOrderId(), productId, product.getProductName(), newCount, product.getProductPrice(),
                                    BigDecimal.valueOf(newCount * product.getProductPrice()).setScale(2, RoundingMode.HALF_UP).doubleValue());
                            hashMap.put(id, orderItem);
                            if (hashMap.get(id).getProductCount() == 0) {
                                hashMap.remove(id);
                            }
                            printOrderList(hashMap);
                            break;
                        }
                    }catch (NullPointerException e){
                        System.out.println("产品id不存在！");
                    }
                }else if ("1".equals(str)){
                    continue outer1;
                }else if ("4".equals(str)){
                    if (hashMap.isEmpty()){
                        System.out.println("购物车现在没有东西哦！快去选购吧ヾ(≧▽≦*)o");
                    }
                    printOrderList(hashMap);
                }else if ("3".equals(str)){
                    hashMap.clear();
                    System.out.println("您的购物车已清空");
                }else {
                    System.out.println("操作序号错误，请重新输入");
                }
            }
            break ;
        }
        /*map转换为list*/
        List<OrderItem> orderItems= new ArrayList<>();
        hashMap.forEach(new BiConsumer<ArrayList<Integer>, OrderItem>() {
            @Override
            public void accept(ArrayList<Integer> integers, OrderItem orderItem){
                orderItems.add(orderItem);
            }
        });
        /*更新订单项*/
        hashMap.forEach(new BiConsumer<ArrayList<Integer>, OrderItem>() {
            @Override
            public void accept(ArrayList<Integer> integers, OrderItem orderItem){
                ois.insertOrderItem(orderItem);
            }
        });
        /*结账*/
        Order bill = os.billPlease(order, orderItems);
        Double discount = as.selectDiscount(bill.getOrderDateTime(), bill.getOrderTotal());
        System.out.println("您的最终订单为："+bill);
        System.out.println("本次您享有的折扣为："+discount);
            Order order1 = os.selectByOrderId(order.getOrderId());
            BufferedWriter  bw = new BufferedWriter(new FileWriter("C:\\Users\\16608\\Desktop\\订单编号-"+order.getOrderId() +".txt"));
            bw.write("您的订单编号为："+order.getOrderId());
            bw.newLine();
            bw.write(order1.toString());
            bw.newLine();
            hashMap.forEach(new BiConsumer<ArrayList<Integer>, OrderItem>() {
                @Override
                public void accept(ArrayList<Integer> integers, OrderItem orderItem){
                    try {
                        bw.write(orderItem.toString());
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            bw.close();
            ias.updateAccount(bill.getOrderTrueTotal());
        enterStaffMenu();
        }catch (InputMismatchException | IOException e){
            System.out.println("输入类型错误，请重新输入");
            /*清除输入缓冲区*/
            input.nextLine();
            SettleAccounts(order);
        }
    }
    /**
     * @description: 打印订单列表方法
     * @return: void
     * @author CYQH
     * @date: 2023/08/21 12:53
     */
    public static void printOrderList(HashMap<ArrayList<Integer>, OrderItem> hashMap){
        hashMap.forEach(new BiConsumer<ArrayList<Integer>, OrderItem>() {
            @Override
            public void accept(ArrayList<Integer> integers, OrderItem orderItem) {
                System.out.print(integers);
                System.out.println(orderItem);
            }
        });
    }
    /**
     * @description: 退货功能，
     * @return: void
     * @author CYQH
     * @date: 2023/08/21 10:06
     */
    public static void returnOfGoods(){
        System.out.println("*********************退货界面*************************");
        /*唯一集合，用于更新替换*/
        HashMap<ArrayList<Integer>, OrderItem> hashMap = new HashMap<>();
        try {
        System.out.println("请输入你要查询的订单编号：");
            int i = input.nextInt();
        Order order = os.selectByOrderId(i);
        if (order.getOrderId() == null){
            System.out.println("此订单编号不存在,请确认后再来");
            enterStaffMenu();
        }
        if (order.getOrderTotal() == null){
            System.out.println("此订单为空，无法退货！");
            enterStaffMenu();
        }
        List<OrderItem> orderItems = ois.selectAllOrderItem(i);
        System.out.println("您订单中包含的商品有：");
        for (OrderItem o:orderItems){
            System.out.println(o);
        }
        System.out.println("订单总价："+order.getOrderTotal()+"     订单实际总价："+order.getOrderTrueTotal());
        /*list转map做唯一集合中转*/
            for (OrderItem item : orderItems) {
                ArrayList<Integer> id = new ArrayList<>();
                id.add(item.getOrderId());
                id.add(item.getProductId());
                hashMap.put(id, item);
            }
        outer3:
        while (true){
            ArrayList<Integer> id = new ArrayList<>();
            id.add(i);
            System.out.println("请输入你要退货的商品id(输入0结束)：");
            int returnId = input.nextInt();
            if (returnId == 0){
                break ;
            }
            if (!orderItems.contains(ois.selectOrderItem(i,returnId))){
                System.out.println("此id不存在或未在您的订单中，请确认后再来");
                returnOfGoods();
            }
            Product product = ps.selectByproductId(returnId);
            id.add(returnId);
            System.out.println("请输入你要退货的数量：");
            int returnCount = input.nextInt();
            Integer productCount = hashMap.get(id).getProductCount();
            if (returnCount > hashMap.get(id).getProductCount()) {
                System.out.println("退货商品数量大于订单商品数量！");
                continue outer3;
            }
            int newCount = productCount-returnCount;
            OrderItem orderItem = new OrderItem(i, product.getProductId(),product.getProductName(), newCount, product.getProductPrice(),
                    BigDecimal.valueOf(newCount * product.getProductPrice()).setScale(2, RoundingMode.HALF_UP).doubleValue());
            hashMap.put(id,orderItem);
            System.out.println("您退货后的订单：");
            hashMap.forEach(new BiConsumer<ArrayList<Integer>, OrderItem>() {
                @Override
                public void accept(ArrayList<Integer> integers, OrderItem orderItem){
                    System.out.println(orderItem);
                }
            });
        }
        /*map转换为list*/
        List<OrderItem> newOrderItems= new ArrayList<>();
        hashMap.forEach(new BiConsumer<ArrayList<Integer>, OrderItem>() {
            @Override
            public void accept(ArrayList<Integer> integers, OrderItem orderItem){
                newOrderItems.add(orderItem);
            }
        });
        System.out.println("----------------------");
        for ( OrderItem o:newOrderItems) {
            System.out.println(o);
        }
        Order order1 = os.returnOfGoods(order, newOrderItems);
        System.out.println("这是您更新后的订单，请收好：");
        System.out.println(order1);
        enterStaffMenu();
        }catch (InputMismatchException e){
            System.out.println("输入类型错误，请重新输入");
            /*清除输入缓冲区*/
            input.nextLine();
            returnOfGoods();
        }
    }

    /**
     * @description: 分页查看活动信息
     * @return: void
     * @author CYQH
     * @date: 2023/08/21 11:11
     */
    public static void empCheckActivity(){
        System.out.println("**********************满减大折扣活动*************************");
        Integer allPages = as.countAllPages();
        /*每页多少条活动*/
        int rows = 3;
        /*查看第几页*/
        int i = 1;
        try {
        while (true){
            List<Activity> activityList = as.selectActivity(i, rows);
            for (Activity activity:activityList) {
                System.out.println(activity);
            }
            if (activityList.isEmpty()){
                System.out.println("已经没有活动了/(ㄒoㄒ)/~~");
                i = (int) (Math.ceil((double) allPages /rows) +1);
            }
            System.out.println("1.上一页；2.下一页；3.跳转~；0.返回上一级菜单");
            System.out.println("当前页数："+i+"总页数"+(int) (Math.ceil((double) allPages /rows)));
            int cio = input.nextInt();
            if (cio == 0){
                enterStaffMenu();
            }else if (cio == 1){
                if (i == 1){
                    System.out.println("已经没有上一页了");
                    i++;
                }
                i--;
            }else if(cio == 2){
                i++;
            }else if (cio == 3){
                System.out.println("请输入想要查看的页数：");
                i = input.nextInt();
                if (i<=0){
                    System.out.println("输入信息不规范，请重新输入");
                    empCheckActivity();
                }
            }else {
                System.out.println("操作序号输入错误，请重新输入！");
            }
        }
        }catch (InputMismatchException e){
            System.out.println("输入类型错误，请重新输入");
            /*清除输入缓冲区*/
            input.nextLine();
            empCheckActivity();
        }
    }
    /**
     * @description: 总经理页面
     * @return: void
     * @author CYQH
     * @date: 2023/08/21 13:30
     */
    public static void enterManagerMenu(){
        System.out.println("*******************欢迎来到管理页面**************************");
        System.out.println("1.对账；2.查询活动；3.添加活动；0.返回上一级菜单");
        System.out.print("请选择您的操作序号：");
        try {
        switch (input.nextInt()){
            case 1:reconciliation(); break;
            case 2:managerCheckActivity(); break;
            case 3:addActivity(); break;
            case 0:Main.AccessPlatform();
        }
        }catch (InputMismatchException e){
            System.out.println("输入类型错误，请重新输入");
            /*清除输入缓冲区*/
            input.nextLine();
            enterManagerMenu();
        }
    }
    /**
     * @description: 对账界面
     * @return: void
     * @author CYQH
     * @date: 2023/08/21 13:31
     */
    public static void reconciliation(){
        System.out.println("*******************对账界面********************");
        System.out.println("1.账户金额查询；2.时间统计订单进账；3.按商品统计商品销售额；0.返回上一级菜单");
        System.out.print("请选择您的操作序号：");
        try {
        switch (input.nextInt()){
            case 1:checkAccount(); break;
            case 2:statisticOrder(); break;
            case 3:statisticProduct(); break;
            case 0:enterManagerMenu(); break;
        }
        }catch (InputMismatchException e){
            System.out.println("输入类型错误，请重新输入");
            /*清除输入缓冲区*/
            input.nextLine();
            reconciliation();
        }
    }
    /**
     * @description: 账户金额查询
     * @return: void
     * @author CYQH
     * @date: 2023/08/21 13:31
     */
    public static void checkAccount(){
        Account account = new Account();
        System.out.println("请输入账户密码：");
        String i = input.next();
        account.setAccountPwd(i);
        System.out.println("你的账户余额是："+ias.selectAccount(account));
        reconciliation();
    }

    /**
     * @description: 时间统计订单进账
     * @return: void
     * @author CYQH
     * @date: 2023/08/21 14:38
     */
    public static void statisticOrder(){
        System.out.println("**********************请按时间格式查询订单进账总额*********************");
        try {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm:ss");
        System.out.println("请输入起始时间（yyyy-MM-dd）");
        LocalDateTime parse1 = LocalDateTime.parse(input.next()+"/00:00:00",df);
        System.out.println("请输入终止时间（yyyy-MM-dd）");
        LocalDateTime parse2 = LocalDateTime.parse(input.next()+"/23:59:59",df);
        Double orderReceipt = os.countMoneyByDate(parse1, parse2);
        System.out.println("您在"+parse1+"到"+parse2+"之间的进账金额为："+orderReceipt);
        reconciliation();
        }catch (Exception e){
            System.out.println("输入类型错误，请重新输入");
            /*清除输入缓冲区*/
            input.nextLine();
            reconciliation();
        }
    }

    /**
     * @description: 按商品统计商品销售额
     * @return: void
     * @author CYQH
     * @date: 2023/08/21 14:53
     */
    public static void statisticProduct(){
        System.out.println("*********************请随意查看各商品销售额****************");
        List<Product> products = ps.selectAllProduct();
        for (Product value : products) {
            System.out.println(value);
        }
        try {
        while (true){
            System.out.println("请输入你要统计的商品（输入0退出）：");
            int productId = input.nextInt();
            Product product = ps.selectByproductId(productId);
            if (productId == 0){
                reconciliation();
            }
            if (product.getProductName() == null){
                System.out.println("您查找的商品不存在");
            } else {

                Double productSales = ois.countByProductId(product);
                List<OrderItem> orderItemList = ois.selectByProductId(product);
                System.out.println("商品编号："+product.getProductId()+"                  商品名称："+product.getProductName());
                for (OrderItem next : orderItemList) {
                    System.out.println("订单编号：" + next.getOrderId() + "商品数量：" + next.getProductCount() +
                            "商品单价：" + next.getProductPrice() + "商品总价：" + next.getProductTotal());
                }
                System.out.println(product.getProductName()+"的总销售额为"+productSales);
            }
        }
        }catch (InputMismatchException e){
            System.out.println("输入类型错误，请重新输入");
            /*清除输入缓冲区*/
            input.nextLine();
            reconciliation();
        }
    }
    /**
     * @description: 添加活动
     * @return: void
     * @author CYQH
     * @date: 2023/08/21 15:18
     */
    public static void addActivity(){
        System.out.println("*********************举办新活动q(≧▽≦q)*********************");
        try {
        Activity activity = new Activity();
        System.out.println("请输入活动名称");
        activity.setActivityName(input.next());
        System.out.println("请输入活动临界金额");
        activity.setCriticalTotal(input.nextDouble());
        while (true){
            System.out.println("请输入活动开始时间(yyyy-MM-dd)：");
            Date startDate = Date.valueOf(input.next());
            System.out.println("请输入活动结束时间(yyyy-MM-dd)：");
            Date endDate = Date.valueOf(input.next());
            if (startDate.after(endDate)){
                System.out.println("活动开始时间必须早于活动结束时间");
            }else {
                activity.setStartDate(startDate);
                activity.setEndDate(endDate);
                break;
            }
        }
        while (true){
            System.out.println("请输入活动折扣");
            double discount = input.nextDouble();
            if (discount >= 1.0 || discount <= 0){
                System.out.println("活动折扣不符合规范，请重新输入");
            }else {
                activity.setDiscount(discount);
                break;
            }
        }
        if (as.insertActivity(activity)){
            System.out.println("活动添加成功");
            enterManagerMenu();
        }
        }catch (Exception e){
            System.out.println("输入类型错误，请重新输入");
            /*清除输入缓冲区*/
            input.nextLine();
            addActivity();
        }
    }
    /**
     * @description: 总经理查询活动，可进行活动的修改和删除
     * @return: void
     * @author CYQH
     * @date: 2023/08/21 15:33
     */
    public static void managerCheckActivity(){
        System.out.println("***********************管理活动页面**********************");
        int allPages = as.countAllPages();
        /*每页多少条活动*/
        int rows = 3;
        /*查看第几页*/
        int i = 1;
        try {
        while (true){
            List<Activity> activityList = as.selectActivity(i, rows);
            for (Activity activity:activityList) {
                System.out.println(activity);
            }
            if (activityList.isEmpty()){
                System.out.println("已经没有活动了/(ㄒoㄒ)/~~");
                i = (int) (Math.ceil((double) allPages /rows) +1);
            }
            System.out.println("     1.上一页；2.下一页；3.跳转~~     ");
            System.out.println("4.修改活动；5.删除活动；0.返回上一级菜单");
            System.out.println("当前页数："+i+"总页数"+(int) (Math.ceil((double) allPages /rows)));
            int cio = input.nextInt();
            if (cio == 0){
                enterManagerMenu();
            }else if (cio == 1){
                if (i == 1){
                    System.out.println("已经没有上一页了");
                    i++;
                }
                i--;
            }else if(cio == 2){
                i++;
            }else if (cio == 4){
                modifyActivity();
            }else if (cio == 5){
                deleteActivity();
            }else if  (cio == 3){
                System.out.println("请输入想要查看的页数：");
                i = input.nextInt();
                if (i<=0){
                    System.out.println("输入信息不规范，请重新输入");
                    empCheckActivity();
                }
            }else {
                System.out.println("操作序号输入错误，请重新输入！");
            }
        }
        }catch (InputMismatchException e){
            System.out.println("输入类型错误，请重新输入");
            /*清除输入缓冲区*/
            input.nextLine();
            managerCheckActivity();
        }
    }
    /**
     * @description: 活动修改功能
     * @return: void
     * @author CYQH
     * @date: 2023/08/21 15:35
     */
    public static void modifyActivity() {
        Activity activity = new Activity();
        while (true){
            System.out.println("请输入你要修改的活动id");
            int activityId = input.nextInt();
            if (as.selectOneActivity(activityId).getActivityName() == null){
                System.out.println("你修改的活动id不存在");
            }else {
                activity.setActivityId(activityId);
                break;
            }
        }
        System.out.println("请输入活动名称");
        activity.setActivityName(input.next());
        System.out.println("请输入活动临界金额");
        activity.setCriticalTotal(input.nextDouble());
        while (true) {
            System.out.println("请输入活动开始时间(yyyy-MM-dd)：");
            Date startDate = Date.valueOf(input.next());
            System.out.println("请输入活动结束时间(yyyy-MM-dd)：");
            Date endDate = Date.valueOf(input.next());
            if (startDate.after(endDate)) {
                System.out.println("活动开始时间必须早于活动结束时间");
            } else {
                activity.setStartDate(startDate);
                activity.setEndDate(endDate);
                break;
            }
        }
        while (true){
            System.out.println("请输入活动折扣");
            double discount = input.nextDouble();
            if (discount >= 1.0 || discount <= 0){
                System.out.println("活动折扣不符合规范，请重新输入");
            }else {
                activity.setDiscount(discount);
                break;
            }
        }
        if (as.updateActivity(activity)){
            System.out.println("活动更新成功");
        }
        managerCheckActivity();
    }
    /**
     * @description: 删除活动功能
     * @return: void
     * @author CYQH
     * @date: 2023/08/21 17:43
     */
    public static void deleteActivity(){
        try {
        int activityId;
        while (true){
            System.out.println("请输入你要删除的活动id");
            activityId = input.nextInt();
            if (as.selectOneActivity(activityId).getActivityName() == null){
                System.out.println("你要删除的活动不存在");
            }else {
                break;
            }
        }
        System.out.println("你确认要删除活动吗？（Y--确认删除）");
        if (input.next().equalsIgnoreCase("Y")){
            as.deleteActivity(activityId);
            System.out.println("活动删除成功！");

        }else {
            System.out.println("活动删除失败");
        }
         managerCheckActivity();
        }catch (InputMismatchException e){
            System.out.println("输入类型错误，请重新输入");
            /*清除输入缓冲区*/
            input.nextLine();
            deleteActivity();
        }
    }
}