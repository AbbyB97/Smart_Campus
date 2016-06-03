package com.example.smart_campus.smartcampus.API;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

public class Attendance
{
  private String student;
  private String objectId;
  private String Dbms;
  private Integer sorting;
  private String Managment;
  private java.util.Date updated;
  private String Java;
  private java.util.Date created;
  private String ownerId;
  private String Embedded;
  public String getStudent()
  {
    return student;
  }

  public void setStudent( String student )
  {
    this.student = student;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getDbms()
  {
    return Dbms;
  }

  public void setDbms( String Dbms )
  {
    this.Dbms = Dbms;
  }

  public Integer getSorting()
  {
    return sorting;
  }

  public void setSorting( Integer sorting )
  {
    this.sorting = sorting;
  }

  public String getManagment()
  {
    return Managment;
  }

  public void setManagment( String Managment )
  {
    this.Managment = Managment;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getJava()
  {
    return Java;
  }

  public void setJava( String Java )
  {
    this.Java = Java;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public String getEmbedded()
  {
    return Embedded;
  }

  public void setEmbedded( String Embedded )
  {
    this.Embedded = Embedded;
  }

                                                    
  public Attendance save()
  {
    return Backendless.Data.of( Attendance.class ).save( this );
  }

  public Future<Attendance> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Attendance> future = new Future<Attendance>();
      Backendless.Data.of( Attendance.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<Attendance> callback )
  {
    Backendless.Data.of( Attendance.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Attendance.class ).remove( this );
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
      Backendless.Data.of( Attendance.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Attendance.class ).remove( this, callback );
  }

  public static Attendance findById( String id )
  {
    return Backendless.Data.of( Attendance.class ).findById( id );
  }

  public static Future<Attendance> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Attendance> future = new Future<Attendance>();
      Backendless.Data.of( Attendance.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<Attendance> callback )
  {
    Backendless.Data.of( Attendance.class ).findById( id, callback );
  }

  public static Attendance findFirst()
  {
    return Backendless.Data.of( Attendance.class ).findFirst();
  }

  public static Future<Attendance> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Attendance> future = new Future<Attendance>();
      Backendless.Data.of( Attendance.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<Attendance> callback )
  {
    Backendless.Data.of( Attendance.class ).findFirst( callback );
  }

  public static Attendance findLast()
  {
    return Backendless.Data.of( Attendance.class ).findLast();
  }

  public static Future<Attendance> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Attendance> future = new Future<Attendance>();
      Backendless.Data.of( Attendance.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<Attendance> callback )
  {
    Backendless.Data.of( Attendance.class ).findLast( callback );
  }

  public static BackendlessCollection<Attendance> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( Attendance.class ).find( query );
  }

  public static Future<BackendlessCollection<Attendance>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<Attendance>> future = new Future<BackendlessCollection<Attendance>>();
      Backendless.Data.of( Attendance.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Attendance>> callback )
  {
    Backendless.Data.of( Attendance.class ).find( query, callback );
  }
}