package com.example.smart_campus.smartcampus.API;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

public class schedule
{
  private String Thursday;
  private String Time;
  private String objectId;
  private String Tuesday;
  private String Wednesday;
  private String Friday;
  private java.util.Date created;
  private String Monday;
  private Integer SEQUENCE;
  private java.util.Date updated;
  private String ownerId;
  private String Saturday;
  public String getThursday()
  {
    return Thursday;
  }

  public void setThursday( String Thursday )
  {
    this.Thursday = Thursday;
  }

  public String getTime()
  {
    return Time;
  }

  public void setTime( String Time )
  {
    this.Time = Time;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getTuesday()
  {
    return Tuesday;
  }

  public void setTuesday( String Tuesday )
  {
    this.Tuesday = Tuesday;
  }

  public String getWednesday()
  {
    return Wednesday;
  }

  public void setWednesday( String Wednesday )
  {
    this.Wednesday = Wednesday;
  }

  public String getFriday()
  {
    return Friday;
  }

  public void setFriday( String Friday )
  {
    this.Friday = Friday;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getMonday()
  {
    return Monday;
  }

  public void setMonday( String Monday )
  {
    this.Monday = Monday;
  }

  public Integer getSEQUENCE()
  {
    return SEQUENCE;
  }

  public void setSEQUENCE( Integer SEQUENCE )
  {
    this.SEQUENCE = SEQUENCE;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public String getSaturday()
  {
    return Saturday;
  }

  public void setSaturday( String Saturday )
  {
    this.Saturday = Saturday;
  }

                                                    
  public schedule save()
  {
    return Backendless.Data.of( schedule.class ).save( this );
  }

  public Future<schedule> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<schedule> future = new Future<schedule>();
      Backendless.Data.of( schedule.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<schedule> callback )
  {
    Backendless.Data.of( schedule.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( schedule.class ).remove( this );
  }

  public Future<Long> removeAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Long> future = new Future<Long>();
      Backendless.Data.of( schedule.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( schedule.class ).remove( this, callback );
  }

  public static schedule findById( String id )
  {
    return Backendless.Data.of( schedule.class ).findById( id );
  }

  public static Future<schedule> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<schedule> future = new Future<schedule>();
      Backendless.Data.of( schedule.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<schedule> callback )
  {
    Backendless.Data.of( schedule.class ).findById( id, callback );
  }

  public static schedule findFirst()
  {
    return Backendless.Data.of( schedule.class ).findFirst();
  }

  public static Future<schedule> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<schedule> future = new Future<schedule>();
      Backendless.Data.of( schedule.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<schedule> callback )
  {
    Backendless.Data.of( schedule.class ).findFirst( callback );
  }

  public static schedule findLast()
  {
    return Backendless.Data.of( schedule.class ).findLast();
  }

  public static Future<schedule> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<schedule> future = new Future<schedule>();
      Backendless.Data.of( schedule.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<schedule> callback )
  {
    Backendless.Data.of( schedule.class ).findLast( callback );
  }

  public static BackendlessCollection<schedule> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( schedule.class ).find( query );
  }

  public static Future<BackendlessCollection<schedule>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<schedule>> future = new Future<BackendlessCollection<schedule>>();
      Backendless.Data.of( schedule.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<schedule>> callback )
  {
    Backendless.Data.of( schedule.class ).find( query, callback );
  }
}