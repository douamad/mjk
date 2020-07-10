import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MjkSharedModule } from 'app/shared/shared.module';
import { OrigineSaisineComponent } from './origine-saisine.component';
import { OrigineSaisineDetailComponent } from './origine-saisine-detail.component';
import { OrigineSaisineUpdateComponent } from './origine-saisine-update.component';
import { OrigineSaisineDeleteDialogComponent } from './origine-saisine-delete-dialog.component';
import { origineSaisineRoute } from './origine-saisine.route';

@NgModule({
  imports: [MjkSharedModule, RouterModule.forChild(origineSaisineRoute)],
  declarations: [
    OrigineSaisineComponent,
    OrigineSaisineDetailComponent,
    OrigineSaisineUpdateComponent,
    OrigineSaisineDeleteDialogComponent,
  ],
  entryComponents: [OrigineSaisineDeleteDialogComponent],
})
export class MjkOrigineSaisineModule {}
