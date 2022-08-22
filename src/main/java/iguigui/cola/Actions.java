package iguigui.cola;

public enum Actions {
    //支付成功
    PAY_SUCCESS,
    //支付失败
    PAY_TIMEOUT,
    //发货调用成功
    SEND_CALL_SUCCESS,
    //发货成功
    SEND_SUCCESS,
    //发货失败
    SEND_FAILED,
    //申请退款
    REFUND_APPLY,
    //核销成功
    VERIFY_SUCCESS,
    //退款审核拒绝
    REFUND_REFUSE,
    //退款审核通过
    REFUND_PASS,
    //退款成功
    REFUND_SUCCESS,

}
