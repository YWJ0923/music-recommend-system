import { Result, Button } from 'antd';
import React from 'react';

export default function Error() {
    return (
        <Result
            status="500"
            title="500"
            subTitle="Sorry, something went wrong."
            extra={<Button type="primary">Back Home</Button>}
        />
    );
}