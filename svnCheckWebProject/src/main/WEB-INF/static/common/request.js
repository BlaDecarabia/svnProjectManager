function doService(url, data, mySuccess, failure, headers) {
    $.ajax({
        url: url,
        contentType: "application/json",
        data: JSON.stringify(data),
        type: "POST",
        beforeSend: function () {
            $.messager.progress({
                title: '提示',
                msg: '系统处理中，请稍候……',
                text: ''
            });
            $("body").find(".messager-window").css({
                "display": "block",
                "z-index": "9035",
                "position": "absolute"
            });
        },
        complete: function () {
            $.messager.progress('close');
        },
        success: function (respData) {
            var rtnCode = respData.rtnCode;
            if (rtnCode == "2000") {
                if (mySuccess) {
                    mySuccess(respData);
                }
            } else {
                if (failure) {
                    failure(respData);
                } else {
                    $.messager.alert("error", respData.errMsg);
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $.messager.alert(textStatus, XMLHttpRequest.status + " - " + XMLHttpRequest.statusText);
        }
    });
}

function doFileUpload(url, data, mySuccess, failure, headers) {
    $.ajax({
      url: url,
      type: "POST",
      data: data,
      contentType: false,
      processData: false,
      beforeSend: function () {
        $.messager.progress({
          title: '提示',
          msg: '系统处理中，请稍候……',
          text: ''
        });
        $("body").find(".messager-window").css({
          "display": "block",
          "z-index": "9035",
          "position": "absolute"
        });
      },
      complete: function () {
        $.messager.progress('close');
      },
      success: function (respData) {
        var rtnCode = respData.rtnCode;
        if (rtnCode == "2000") {
          if (mySuccess) {
            mySuccess(respData);
          }
        } else {
          if (failure) {
            failure(respData);
          } else {
            $.messager.alert("error", respData.errMsg);
          }
        }
      },
      error: function (XMLHttpRequest, textStatus, errorThrown) {
        $.messager.alert(textStatus, XMLHttpRequest.status + " - " + XMLHttpRequest.statusText);
      }
    })
}
