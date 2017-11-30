/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\admin\\Desktop\\项目源代码\\Android教材源码\\chapter08\\支付宝\\src\\cn\\itcast\\alipay\\IService.aidl
 */
package cn.itcast.alipay;
public interface IService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements cn.itcast.alipay.IService
{
private static final java.lang.String DESCRIPTOR = "cn.itcast.alipay.IService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an cn.itcast.alipay.IService interface,
 * generating a proxy if needed.
 */
public static cn.itcast.alipay.IService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof cn.itcast.alipay.IService))) {
return ((cn.itcast.alipay.IService)iin);
}
return new cn.itcast.alipay.IService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_callALiPayService:
{
data.enforceInterface(DESCRIPTOR);
this.callALiPayService();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements cn.itcast.alipay.IService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void callALiPayService() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_callALiPayService, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_callALiPayService = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void callALiPayService() throws android.os.RemoteException;
}
