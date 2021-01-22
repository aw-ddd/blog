export default {

  //时间转换器---start
  dateUtils (d) {
    if (!d) {
      return "";
    }
    let flag = typeof (d) == 'string'
    if (flag) {
      return d;
    }
    var year = d.getFullYear();
    var month = this.prefixInteger(d.getMonth() + 1);
    var date = this.prefixInteger(d.getDate());
    var hours = this.prefixInteger(d.getHours());
    var minutes = this.prefixInteger(d.getMinutes());
    var seconds = this.prefixInteger(d.getSeconds());
    return year + '-' + month + '-' + date + ' ' + hours + ':' + minutes + ':' + seconds;
  },

  //自动补零
  prefixInteger (num) {
    if (num > 9) {
      return num;
    }
    return (Array(2).join(0) + num).slice(-2);
  },
  //时间转换器---end
  formatLongDate (date, fmt) {
    date = date == undefined ? new Date() : date;
    date = typeof date == 'number' ? new Date(date) : date;
    fmt = fmt || 'yyyy-MM-dd HH:mm:ss';
    var obj = {
      'y': date.getFullYear(), // 年份，注意必须用getFullYear
      'M': date.getMonth() + 1, // 月份，注意是从0-11
      'd': date.getDate(), // 日期
      'q': Math.floor((date.getMonth() + 3) / 3), // 季度
      'w': date.getDay(), // 星期，注意是0-6
      'H': date.getHours(), // 24小时制
      'h': date.getHours() % 12 == 0 ? 12 : date.getHours() % 12, // 12小时制
      'm': date.getMinutes(), // 分钟
      's': date.getSeconds(), // 秒
      'S': date.getMilliseconds() // 毫秒
    };
    var week = ['天', '一', '二', '三', '四', '五', '六'];
    for (var i in obj) {
      fmt = fmt.replace(new RegExp(i + '+', 'g'), function (m) {
        var val = obj[i] + '';
        if (i == 'w') return (m.length > 2 ? '星期' : '周') + week[val];
        for (var j = 0, len = val.length; j < m.length - len; j++) val = '0' + val;
        return m.length == 1 ? val : val.substring(val.length - m.length);
      });
    }
    return fmt;
  },

  /**
   * 将中国标准时间成指定格式的字符串

   */
  formatStandardDate (date, fmt) {
    //取年月日
    var arr = date.split("T");
    var d = arr[0];
    var darr = d.split('-');
    //取时分秒
    var t = arr[1];
    var tarr = t.split('.000');
    var marr = tarr[0].split(':');
    //根据传过来的格式进行判断
    var dd;
    fmt = fmt || 'yyyy-MM-dd HH:mm:ss';
    if (fmt == 'yyyy/MM/dd HH:mm:ss') {
      dd = parseInt(darr[0]) + "/" + parseInt(darr[1]) + "/" + parseInt(darr[2]) + " " + parseInt(marr[0]) + ":" + parseInt(marr[1]) + ":" + parseInt(marr[2]);
      return dd;
    } else if (fmt == 'yyyy/MM/dd') {
      dd = parseInt(darr[0]) + "/" + parseInt(darr[1]) + "/" + parseInt(darr[2]);
      return dd;
    } else if (fmt == 'yyyy-MM-dd HH:mm:ss') {
      dd = parseInt(darr[0]) + "-" + parseInt(darr[1]) + "-" + parseInt(darr[2]) + " " + parseInt(marr[0]) + ":" + parseInt(marr[1]) + ":" + parseInt(marr[2]);
      return dd;
    } else if (fmt == 'yyyy-MM-dd') {
      dd = parseInt(darr[0]) + "-" + parseInt(darr[1]) + "-" + parseInt(darr[2]);
      return dd;
    }
  },
  //传入标准日期，返回常用日期：
  getStandardDate : function (date) {
    return this.formatStandardDate(date, 'yyyy/MM/dd')
  },
  //传入标准日期，返回常用日期+时间：
  getStandardDateTime (date) {
    return this.formatStandardDate(date, 'yyyy/MM/dd HH:mm:ss')
  },
  //传入字符戳，返回常用日期：
  getLongDate (date) {
    return this.formatLongDate(date, 'yyyy/MM/dd')
  },
  //传入字符戳，返回常用日期+时间：
  getLongDateTime :function (date) {
    console.log(this)
    return this.formatLongDate(date, 'yyyy/MM/dd HH:mm:ss')
  },
  //获取当前时间戳
  getTimestamp () {
    let timestamp = (new Date()).getTime();
    return timestamp;
  },
  //计算与当前的时间差，传入需比较的时间
  getTimedif (val) {
    //当前时间戳
    let timestamp = (new Date()).valueOf();
    //相差多少分钟
    return (timestamp - val) / (1000 * 60)
  }
}
