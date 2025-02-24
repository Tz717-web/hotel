import myAxios from "@/request";
interface FormState {
  userAccount: string; // 用户账号
  userPassword: string; // 用户密码
  checkPassword: string; // 确认密码
  remember: boolean; // 是否同意条约
  roomNumber: string; // 房间号
  planetCode: string; //编号
}
/**
 * 用户注册
 * @param params
 */
// 导出一个名为 userRegister 的异步函数，用于用户注册
export const userRegister = async (params: FormState) => {
  // 使用 myAxios 库发送 HTTP 请求
  return myAxios.request({
    // 请求的 URL 地址，指向用户注册的 API 接口
    url: "/api/user/register",
    // 请求方法为 POST，表示提交数据
    method: "POST",
    // 请求的数据，即用户注册的表单信息
    data: params,
  });
};

/**
 * 用户登录
 * @param params
 */
export const userLogin = async (params: FormState) => {
  return myAxios.request({
    url: "/api/user/login",
    method: "POST",
    data: params,
  });
};

/**
 * 用户注销
 * @param params
 */
export const userLogout = async (params: unknown) => {
  return myAxios.request({
    url: "/api/user/logout",
    method: "POST",
    data: params,
  });
};

/**
 * 获取当前用户
 */
export const getCurrentUser = async () => {
  return myAxios.request({
    url: "/api/user/current",
    method: "GET",
  });
};

/**
 * 获取用户列表
 * @param userAccount
 */
export const searchUsers = async (userAccount: unknown) => {
  return myAxios.request({
    url: "/api/user/search",
    method: "GET",
    params: {
      userAccount,
    },
  });
};

/**
 * 删除用户
 * @param id
 */
export const deleteUser = async (id: string) => {
  return myAxios.request({
    url: "/api/user/delete",
    method: "POST",
    data: id,
    // 关键点：要传递 JSON 格式的值
    headers: {
      "Content-Type": "application/json",
    },
  });
};
interface users {
  username: string;
  userAccount: string;
  userPassword: string;
}
export const updateUser = async (params: users) => {
  // 使用 myAxios 实例发送一个 HTTP 请求
  return myAxios.request({
    // 请求的 URL 地址为 "/api/hotel/update"
    url: "/api/user/update",
    // 请求方法为 PUT，通常用于更新资源
    method: "PUT",
    // 请求的数据为传入的 params 参数
    data: params,
  });
};

//预约功能接口
interface hotel {
  id: string;
  idCard: string;
  phoneNumber: string;
  checkInDate: string;
  checkOutDate: string;
  specialRequests: string;
  roomType: string;
  reservationStatus: string;
  numberOfRooms: string;
}
/**
 * 预约房间接口
 * @param params
 */
// 导出一个名为 hotelRegister 的异步函数，该函数接收一个类型为 hotel 的参数 params
export const hotelRegister = async (params: hotel) => {
  // 使用 myAxios 实例发送一个 HTTP 请求
  // 请求的 URL 为 "/api/hotel/register"
  // 请求方法为 POST
  // 请求的数据为传入的参数 params
  return myAxios.request({
    url: "/api/hotel/register",
    method: "POST",
    data: params,
  });
};
// 导出一个名为 getAllHotels 的异步函数
export const hotelSearch = async (phoneNumber: unknown) => {
  // 使用 myAxios 实例发送一个 HTTP 请求
  // 请求的 URL 为 "/api/hotel/all"
  // 请求的方法为 GET
  return myAxios.request({
    url: "/api/hotel/search",
    method: "GET",
    params: {
      phoneNumber,
    },
  });
};
// 导出一个名为 deleteHotel 的异步函数，该函数接收一个字符串类型的参数 id
export const deleteHotel = async (id: string) => {
  // 使用 myAxios 实例发送一个 HTTP 请求
  return myAxios.request({
    // 请求的 URL 地址为 "/api/user/delete"
    url: "/api/hotel/delete",
    // 请求方法为 POST
    method: "POST",
    // 请求的数据为传入的 id 参数
    data: id,
    // 关键点：要传递 JSON 格式的值
    headers: {
      "Content-Type": "application/json",
    },
  });
};
// 导出一个名为 hotelUpdate 的异步函数，该函数接收一个类型为 hotel 的参数 params
export const hotelUpdate = async (params: hotel) => {
  // 使用 myAxios 实例发送一个 HTTP 请求
  return myAxios.request({
    // 请求的 URL 地址为 "/api/hotel/update"
    url: "/api/hotel/update",
    // 请求方法为 PUT，通常用于更新资源
    method: "PUT",
    // 请求的数据为传入的 params 参数
    data: params,
  });
};
