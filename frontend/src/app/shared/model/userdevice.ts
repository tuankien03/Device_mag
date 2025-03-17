export interface UserDevice {
    id: number;
    deviceName: string;
    descriptionDevice: string;
    statusDevice: string;
    username: string;
    role: string;
    userId: string;
    deviceId: string;
    assignedAt: string;
    returnedAt: string;
}

export const USERDEVICE_KEYS = [
    'deviceName',
    'descriptionDevice',
    'statusDevice',
    'username',
    'assignedAt',
    'returnedAt'
];

export const USERDEVICE_DISPLAYED_COLUMNS = {
    deviceName: 'Tên thiết bị',
    descriptionDevice: 'Mô tả',
    statusDevice: 'Trạng thái',
    username: 'Người dùng',
    assignedAt: 'Ngày mượn',
    returnedAt: 'Ngày trả lại'
};

export const USERDEVICE_DISPLAYED_COLUMNS_HEADER = {
    deviceName: 'Tên thiết bị',
    descriptionDevice: 'Mô tả',
    statusDevice: 'Trạng thái',
    username: 'Người dùng',
    assignedAt: 'Ngày mượn',
    returnedAt: 'Ngày trả lại'
};