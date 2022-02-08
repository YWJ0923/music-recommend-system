import {notification} from "antd";

export function showNotification(message) {
    notification.open({
        message: message,
        placement: 'bottomRight',
        duration: 2
    });
}