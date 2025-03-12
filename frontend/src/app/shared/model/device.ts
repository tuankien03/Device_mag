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

