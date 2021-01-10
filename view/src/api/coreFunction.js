//核心功能

//保存md文件
export function saveMd(data) {
  return this.$request.post('saveMd',data,(res)=>{
    console.log(res);
  })
}

export function test(data) {
  console.log(data);
  return data
}
