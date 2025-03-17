export interface Device {
    deviceId: string;
    name: string;
    description: string;
    status: DeviceStatus;
    createdAt: string;
    updatedAt: string;
}

export const DEVICE_KEYS: (keyof Device)[] = [
    'name',
    'description',
    'status',
    'createdAt',
    'updatedAt'
];

export enum DeviceStatus {
    AVAILABLE = "ACTIVE",
    RETURNING = "RETURNING",
    ASSIGNED = "ASSIGNED"
}

export const DEVICE_DISPLAYED_COLUMNS = {
    name: 'Tên thiết bị',
    description: 'Mô tả',
    status: 'Trạng thái',
    createdAt: 'Ngày tạo',
    updatedAt: 'Ngày cập nhật'
};

