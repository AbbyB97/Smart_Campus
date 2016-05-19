package com.example.smart_campus.smartcampus.API;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

public class Notice
{
  private java.util.Date updated;
  private java.util.Date created;
  private String Title;
  private String ownerId;
  private String From;
  private String objectId;
  private String Content;
  public java.util.Date getUpdated()
  {
    return updated;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getTitle()
  {
    return Title;
  }

  public void setTitle( String Title )
  {
    this.Title = Title;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public String getFrom()
  {
    return From;
  }

  public void setFrom( String From )
  {
    this.From = From;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getContent()
  {
    return Content;
  }

  public void setContent( String Content )
  {
    this.Content = Content;
  }

                                                    
  public Notice save()
  {
    return Backendless.Data.of( Notice.class ).save( this );
  }

  public Future<Notice> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Notice> future = new Future<Notice>();
      Backendless.Data.of( Notice.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<Notice> callback )
  {
    Backendless.Data.of( Notice.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Notice.class ).remove( this );
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
      Backendless.Data.of( Notice.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Notice.class ).remove( this, callback );
  }

  public static Notice findById( String id )
  {
    return Backendless.Data.of( Notice.class ).findById( id );
  }

  public static Future<Notice> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Notice> future = new Future<Notice>();
      Backendless.Data.of( Notice.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<Notice> callback )
  {
    Backendless.Data.of( Notice.class ).findById( id, callback );
  }

  public static Notice findFirst()
  {
    return Backendless.Data.of( Notice.class ).findFirst();
  }

  public static Future<Notice> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Notice> future = new Future<Notice>();
      Backendless.Data.of( Notice.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<Notice> callback )
  {
    Backendless.Data.of( Notice.class ).findFirst( callback );
  }

  public static Notice findLast()
  {
    return Backendless.Data.of( Notice.class ).findLast();
  }

  public static Future<Notice> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Notice> future = new Future<Notice>();
      Backendless.Data.of( Notice.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<Notice> callback )
  {
    Backendless.Data.of( Notice.class ).findLast( callback );
  }

  public static BackendlessCollection<Notice> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( Notice.class ).find( query );
  }

  public static Future<BackendlessCollection<Notice>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<Notice>> future = new Future<BackendlessCollection<Notice>>();
      Backendless.Data.of( Notice.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Notice>> callback )
  {
    Backendless.Data.of( Notice.class ).find( query, callback );
  }
}